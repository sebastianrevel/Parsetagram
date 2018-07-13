package me.sebastianrevel.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        if (ParseUser.getCurrentUser() != null) {
            final Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }

        usernameInput = findViewById(R.id.username_et);
        passwordInput = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                login(username, password);
            }
        });
        signUpBtn = findViewById(R.id.signup_btn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                signup(username, password);
            }
        });
    }

    private void signup(final String username, final String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        Log.d("TEST", username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("LoginActivity", "SignUp Successful");
                    login(username, password);
                } else {
                    Log.e("LoginActivity", "SignUp Failure");
                    e.printStackTrace();
                }
            }
        });
    }

    private void login(String username, final String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.d("LoginActivity", "Login Successful");
                    final Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Log.e("LoginActivity", "Login Failure");
                    e.printStackTrace();
                }
            }
        });
    }
}

