package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.HashMap;

import datamanagement.LocalReader;
import datamanagement.Reader;
import processor.Processor;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private TextView forgotLogin;
    private int incorrectCounter = 0;

    public Reader local;
    public static Processor processor;

    private Button newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up LocalReader and Processor
        local = new LocalReader();
        processor = new Processor(local);


        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.loginButton);
        forgotLogin = findViewById(R.id.tvForgotLogin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

        newAccount = findViewById(R.id.newAccountButton);
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }


    //Want to access database for login credentials here
    private void validate (String inputUsername, String inputPassword){
        if (processor.validateLogin(inputUsername, inputPassword)){
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
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
}
