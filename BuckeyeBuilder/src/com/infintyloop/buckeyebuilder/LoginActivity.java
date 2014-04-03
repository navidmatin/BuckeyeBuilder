package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;
import java.util.List;

import com.infintyloop.buckeyebuilder.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.infintyloop.buckeyebuilder.BuildingFactory;
import com.infintyloop.buckeyebuilder.IUser;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	private Intent intent;
	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
			"foo@example.com:hello", "bar@example.com:world" };
	private static final String OPT_NAME="name";
	/**
	 * The default username to populate the username field with.
	 */
	public static final String EXTRA_username = "com.example.android.authenticatordemo.extra.username";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for username and password at the time of the login attempt.
	private String musername;
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
		// Set up the login form.
		musername = getIntent().getStringExtra(EXTRA_username);
		musernameView = (EditText) findViewById(R.id.username);
		musernameView.setText(musername);

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
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		musernameView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		musername = musernameView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
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
			}
			
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
		BuildingFactory myFactory = new BuildingFactory();
		IUser user = new User();
		
		LocationHandler myHandler = new LocationHandler();
		
    	SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(this);
    	SharedPreferences.Editor editor=settings.edit();
        editor.putString(OPT_NAME, username);
        editor.commit();
        
        intent=new Intent(this, MainActivity.class);
		myFactory.MakeBuildings();
		// Using username, access database and fill in info for user
		// and levels of buildings owned
		user.GiveValuesToUser(musername, 1000, 300);
        intent.putExtra("User", user);
		myFactory.AssignLevels(new int[] {2,1,0}, user); //HardCoded Building Generator
		ArrayList<IBuilding> myBuildings = myFactory.ReturnBuildingList();
		myHandler.Initialize(myBuildings);
        intent.putExtra("LocationHandler", myHandler);
		intent.putExtra("BuildingList", myBuildings);
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

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(musername)) {
					// Account exists, return true if the password matches.
					return pieces[1].equals(mPassword);
				}
			}

			// TODO: register the new account here.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				finish();
			} else {
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
