package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private TextView forgotLogin;
    private int incorrectCounter = 0;
    static HashMap<String, String> loginCredentials = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginCredentials.put("Prafful", "password1234");
        loginCredentials.put("Yunhee", "anotherpassword");
        loginCredentials.put("Chris", "1");

        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.loginButton);
        forgotLogin = (TextView) findViewById(R.id.tvForgotLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

    }


    //Want to access database for login credentials here
    private void validate (String inputUsername, String inputPassword){
        if (checkLoginCredentials(inputUsername, inputPassword)){
            Intent intent = new Intent(MainActivity.this, SecondPage.class);
            startActivity(intent);
        }
        else{
            incorrectCounter++;
            if (incorrectCounter < 3){
                forgotLogin.setText("Incorrect Credentials");

            }
            else if(incorrectCounter < 10){
                forgotLogin.setText("Incorrect Credentials. \nPlease contact your administrator at: admin@email.com");
            }
            else {
                forgotLogin.setText("Login has been disabled! \nPlease contact your administrator at: admin@email.com");
                login.setEnabled(false);
            }
        }
    }
    //This method for now will be hardcoded with Usernames and Passwords (maybe like a Map or something)
    //Eventually, we'll look towards making Database queries
    private boolean checkLoginCredentials(String us, String pw){
        if (loginCredentials.containsKey(us)){
            return loginCredentials.get(us).equals(pw);
        }
        return false;
    }
}
