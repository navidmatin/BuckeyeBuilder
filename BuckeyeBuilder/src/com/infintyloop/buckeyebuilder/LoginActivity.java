package com.infintyloop.buckeyebuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.infinityloop.buckeyebuilder.databasehelper.DataHandler;
import com.infinityloop.buckeyebuilder.databasehelper.DatabaseHelper;
import com.infintyloop.buckeyebuilder.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.infintyloop.buckeyebuilder.BuildingFactory;
import com.infintyloop.buckeyebuilder.IUser;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	private Intent intent;

	private static final String OPT_NAME="name";
	/**
	 * The default username to populate the username field with.
	 */
	public static final String EXTRA_username = "com.example.android.authenticatordemo.extra.username";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	//Facebook
	
	// Values for username and password at the time of the login attempt.
	private String mUsername;
	private String mPassword;

	// UI references.
	private EditText musernameView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	private DatabaseHelper dh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		//Parse Stuff
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		if(currentUser!=null)
		{
			setupUser(mUsername);
			startActivity(intent);
			finish();
		}
		// Set up the login form.
		mUsername = getIntent().getStringExtra(EXTRA_username);
		musernameView = (EditText) findViewById(R.id.username);
		musernameView.setText(mUsername);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
		findViewById(R.id.create_new_user_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						createNewUser();
					}
				});		
		//FACEBOOK LOGIN SHOWING
		findViewById(R.id.authButton).setOnClickListener(
				new View.OnClickListener(){
					@Override
					public void onClick(View view){
						facebookLogin();
				}
				});
	}
	protected void facebookLogin(){
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid username, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {

		// Reset errors.
		musernameView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mUsername = musernameView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_short_password));
			focusView = mPasswordView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			showProgress(true);
			ParseUser.logInInBackground(mUsername, mPassword, new LogInCallback() {
				  public void done(ParseUser user, ParseException e) {
				    if (user != null) {
				    	mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
				    	setupUser(mUsername);
				    	startActivity(intent);
						finish();
				      // Hooray! The user is logged in.
				    } else {
				    	showProgress(false);
				    	mPasswordView.setError(getString(R.string.error_invalid_password));
				      // Signup failed. Look at the ParseException to see what happened.
				    }
				  }
				});
			/*
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			if(checkLogin())
			{				
				showProgress(true);
				mAuthTask = new UserLoginTask();
				mAuthTask.execute((Void) null);
				startActivity(intent);
				finish();
			}*/
			
		}
	}
	private boolean checkLogin(){
    	String username=this.musernameView.getText().toString();
        String password=this.mPasswordView.getText().toString();
        this.dh=new DatabaseHelper(this);
        List<String> names=this.dh.selectAll(username,password);
        if(names.size() >0){ // Login successful
        	// Save username as the name of the player & Set up the initial values for the user
            setupUser(username);
            return true;
        	
        } else {
            // Try again? 
        	new AlertDialog.Builder(this)
    		.setTitle("Error")
    		.setMessage("Login failed")
    		.setNeutralButton("Try Again", new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int which) {}
    		})
    		.show();
        	return false;
        }	
	}
	private void setupUser(String username){
		
		intent=new Intent(this, MainActivity.class);
		//Check to see if we have user in the savedFile
		IUser user = DataHandler.getUserData(this, mUsername);
		if(user==null){
			user=new User();
			// given user, enter their cash and cap values..
			user.GiveValuesToUser(mUsername, 1000, 300);
			user.IncreaseNumberofBuildingsOwned(2);
		}
		intent.putExtra("User", user);
	    //Check to see if we have buildings in the savedfile otherwise create new ones
		ArrayList<Building> buildingList=DataHandler.getBuildingListData(this, mUsername);
		if(buildingList==null)
		{
			BuildingFactory myFactory = new BuildingFactory();
			myFactory.MakeBuildings();
			myFactory.AssignLevels(new int[] {2,1,0}, user); 		
			buildingList = myFactory.ReturnBuildingList();
		}
		intent.putExtra("BuildingList", buildingList);
	}
	public void createNewUser() {
		startActivity(new Intent(this, RegisterActivity.class));
	}
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
	//Facebook single-sign in
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		  ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}
}
