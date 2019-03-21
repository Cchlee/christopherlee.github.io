package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import data.*;


public class CreateAccount extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private RadioGroup accountTypes;
    private boolean isUser = false;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Name and Password
        name = findViewById(R.id.newAccName);
        password = findViewById(R.id.newAccPassword);

        // Which account (user or leader)
        accountTypes = findViewById(R.id.accountType);
        accountTypes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.User:
                        isUser = true;
                        break;
                    default:
                        isUser = false;
                        break;
                }
            }
        });

        // Create account and how to proceed from there
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
    }

    private void checkUser() {
        if (isUser) {
            User user = new User(name.getText().toString(), password.getText().toString());
            Intent intent = new Intent(CreateAccount.this, UserSchedule.class);
            intent.putExtra("User", user);
            startActivity(intent);
        } else {
            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
