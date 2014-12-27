package com.persopolissoftware.buckeyebuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.model.GraphUser;
import com.persopolissoftware.buckeyebuilder.Core.Building;
import com.persopolissoftware.buckeyebuilder.Core.BuildingFactory;
import com.persopolissoftware.buckeyebuilder.Core.IUser;
import com.persopolissoftware.buckeyebuilder.Core.User;
import com.persopolissoftware.buckeyebuilder.Core.Utility;
import com.persopolissoftware.buckeyebuilderUtilities.databasehelper.DataHandler;
import com.persopolissoftware.buckeyebuilderUtilities.databasehelper.DatabaseHelper;
import com.persopolissoftware.buckeyebuilder.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

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
	private EditText input;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		//Parse Stuff
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		if(currentUser!=null)
		{
			setupUser(currentUser.getUsername());
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
						showProgress(true);
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
						showProgress(true);
                        facebookLogin();
						
				}
				});
	}
	protected void facebookLogin(){

            Session.openActiveSession(this, true, new Session.StatusCallback() {
                @Override
                public void call (Session session, SessionState state, Exception exception){
                    if(session.isOpened()){
                        Request.newMeRequest(session, new Request.GraphUserCallback() {
                            @Override
                            public void onCompleted(GraphUser user, Response response) {
                                if(user!=null){
                                    mUsername=user.getName();
                                }
                            }
                        }).executeAsync();
                    }
                }
            });
            List<String> permissions = Arrays.asList("public_profile", "email");
            ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException err) {
                    if (user == null) {
                        //Log out, and try again
                        if(ParseFacebookUtils.getSession()!=null)
                            ParseFacebookUtils.getSession().closeAndClearTokenInformation();
                        ParseUser.logOut();
                        showProgress(false);
                        if(!Utility.isConnectedToInternet(getCurrentContext()))
                        {
                            showProgress(false);
                            Utility.showTextAlertDialog(getCurrentContext(), "No Internet Connection", "Facebook is currently not available. (check your internet connection)");
                        }
                        Log.d("com.persopolissoftware.buckeyebuilder","Uh oh. The user cancelled the Facebook login.");
                    } else if (user.isNew()) {
                        Log.d("com.persopolissoftware.buckeyebuilder",
                                "User signed up and logged in through Facebook!");
                        //FACEBOOK STUFF
                        //setupUser(user.getUsername());
                        //startActivity(intent);
                        promptForUsername();
                        showProgress(true);

                    } else {
                        Log.d("com.persopolissoftware.buckeyebuilder",
                                "User logged in through Facebook!");
                        setupUser(user.getUsername());
                        startActivity(intent);
                        finish();
                    }
                }
            });

	}
	private void promptForUsername()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Username");
		alert.setMessage("Buckeye Builder master needs a name!:");

		// Set an EditText view to get user input 
		input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  mUsername = input.getText().toString();
			  Boolean correct=true;
			  if(mUsername=="" || mUsername.length()==0)
				  correct=false;
			  else
			  {
				  Pattern p = Pattern.compile(".*\\W+.*");
				  Matcher m = p.matcher(mUsername);
				  if(m.find())
					  correct=false;
			  }
			  if(correct)
			  {
				  ParseUser currentUser = ParseUser.getCurrentUser();
				  currentUser.setUsername(mUsername);
				  setupUser(mUsername);
				  startActivity(intent);
				  
				  finish();
			  }
			  else
			  {
			
				 Toast t= Toast.makeText(
                          getApplicationContext(),
                          "Wrong username format, please try again",
                          Toast.LENGTH_SHORT);
				  t.setGravity(Gravity.CENTER, 0, 0);
				  t.show();
				  promptForUsername();
			  }
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			  //When it gets to this level there is always a Facebook session so there is no need to check for it
			ParseFacebookUtils.getSession().closeAndClearTokenInformation();
			  if(ParseUser.getCurrentUser()!=null)
					ParseUser.getCurrentUser().deleteEventually();
			  showProgress(false);
		  }
		});

		alert.show();
		// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog
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

        // Check for a valid username
        if (TextUtils.isEmpty(mUsername)) {
        musernameView.setError(getString(R.string.error_field_required));
        focusView = musernameView;
        cancel = true;
    }

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
            showProgress(false);
		} else {
			if(Utility.isConnectedToInternet(this))
			{
				
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
			}
			else {
                Utility.showTextAlertDialog(this, "No Internet Connection", this.getString(R.string.no_internet));
                showProgress(false);
            }
			
		}
	}
	private void setupUser(String username){
		//Check for internet connectivity
		
				intent=new Intent(this, SplashScreen.class);
				//Check to see if we have user in the savedFile
				IUser user = DataHandler.getUserData(this, username);
				if(user==null){
					user=new User();
					// given user, enter their cash and cap values..
					user.GiveValuesToUser(username, 3000000, 1000000);
				}
				intent.putExtra("User", user);
			    //Check to see if we have buildings in the savedfile otherwise create new ones
				ArrayList<Building> buildingList=DataHandler.getBuildingListData(this, username);
				if(buildingList==null)
				{
					BuildingFactory myFactory = new BuildingFactory();
					myFactory.MakeBuildings();
					buildingList = myFactory.ReturnBuildingList();
				}
				intent.putExtra("BuildingList", buildingList);
		
	}
	public void createNewUser() {
		if(Utility.isConnectedToInternet(this))
			startActivity(new Intent(this, RegisterActivity.class));
		else
			Utility.showTextAlertDialog(this, "No internet connect", getString(R.string.no_internet));
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
    //SUPER HACKISH!!! FIX THIS!!!
    private Context getCurrentContext()
    {
        return this;
    }

	
}
