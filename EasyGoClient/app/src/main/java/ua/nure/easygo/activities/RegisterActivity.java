package ua.nure.easygo.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import easygo.nure.ua.easygoclient.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.model.User;
import ua.nure.easygo.rest.RestService;


public class RegisterActivity extends BaseActivity {

    View registerForm;
    View progressBar;
    EditText mLogin;
    EditText mPassword, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (EditText) findViewById(R.id.login);
        mEmail = (EditText) findViewById(R.id.email);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        registerForm = findViewById(R.id.login_form);
        progressBar = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {
        /** if (mAuthTask != null) {
         return;
         }*/

        // Reset errors.
        mLogin.setError(null);
        mPassword.setError(null);
        mEmail.setError(null);

        // Store values at the time of the login attempt.
        String login = mLogin.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (!isLoginValid(login)) {
            mLogin.setError(getString(R.string.error_invalid_login));
            focusView = mLogin;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            final User user = new User(email, login, password);


            RestService.get().getUser(login).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() == null) {
                        RestService.get().postUser(user).enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Can`t register user with this login", Toast.LENGTH_LONG).show();
                        showProgress(false);
                        //finish();
                        //startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("qaz", t.getMessage());
                    RestService.get().postUser(user).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            finish();
                        }
                    });
                }
            });


        }
    }

    private boolean isLoginValid(String login) {
        return login.length() >= 3;
    }

    private boolean isPasswordValid(String password) {

        return password.length() >= 6;
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
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            registerForm.setVisibility(show ? View.GONE : View.VISIBLE);
            registerForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    registerForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            registerForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}
