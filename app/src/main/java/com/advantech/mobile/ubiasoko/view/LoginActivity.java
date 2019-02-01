package com.advantech.mobile.ubiasoko.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.MainActivity;
import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.loginmanager.SessionManager;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {




    // UI references.
    private TextInputLayout tv_username,tv_password;
    private EditText mPasswordView,mUsernameView;
    Button mEmailSignInButton;

    DBHandler dbHandler;
    public static  final String USER_NAME = "USERNAME";

    //add session manager
    private SessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        // Set up the login form.

        tv_username = (TextInputLayout) findViewById(R.id.tv_username);
        mUsernameView = (EditText)  findViewById(R.id.username);
        mUsernameView.addTextChangedListener(new MyTextWatcher(mUsernameView));


        tv_password =(TextInputLayout) findViewById(R.id.tv_password);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.addTextChangedListener(new MyTextWatcher(mPasswordView));

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQlite();
            }
        });

        dbHandler = new DBHandler(this);

        //session Manager
        session = new SessionManager(getApplicationContext());

        //check if the users is already logged in
        if(session.isLoggedIn()){
            //if user is logged in take him to the main screen
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);

        }
    }

    private void verifyFromSQlite() {
        if(!validateUsername()){
            return;
        }

        if(!validatePassword()){
            return;
        }




            if(dbHandler.checkUser(mUsernameView.getText().toString().trim(),
                    mPasswordView.getText().toString().trim())){

                session.setLogin(true);

                Intent accountsIntent;accountsIntent = new Intent(LoginActivity.this,MainActivity.class);
                accountsIntent.putExtra(USER_NAME,mUsernameView.getText().toString().trim());
                //emptyInputEditText();


                startActivity(accountsIntent);
            }else{
                // Snackbar.make(nestedScrollView,getString(R.string.error_valid_email_password),
                //  Snackbar.LENGTH_LONG).show();
                Toast.makeText(this,R.string.error_valid_email_password,Toast.LENGTH_LONG).show();
            }


    }

    private void emptyInputEditText() {
        mUsernameView.setText("");
        mPasswordView.setText("");
    }


    /*Create an inner class */
    private class MyTextWatcher implements TextWatcher {
        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch(view.getId()){
                case R.id.username:
                    validateUsername();

                    break;

                case R.id.password:
                    validatePassword();

                    break;

            }


        }
    }//end the inner class


    /*--Request edit text focus*/
    private void requestFocus(View view){
        if(view.requestFocus()){
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }//end fn

    private boolean validatePassword() {
        if(mPasswordView.getText().toString().trim().isEmpty()){
            mPasswordView.setError(getString(R.string.err_password));
            requestFocus(mPasswordView);
            return false;
        }else{
            mPasswordView.setError(null);
        }
        return true;
    }

    private boolean validateUsername() {
        if(mUsernameView.getText().toString().trim().isEmpty()){
            mUsernameView.setError(getString(R.string.err_username));
            requestFocus(mUsernameView);
            return false;
        }else{
            mUsernameView.setError(null);
        }
        return true;
    }


}

