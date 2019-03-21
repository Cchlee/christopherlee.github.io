package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import data.*;

public class CreateCommunity extends AppCompatActivity {
    private EditText name;
    private EditText description;
    private Button createCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);

        // Name and Description
        name = findViewById(R.id.newCommName);
        description = findViewById(R.id.newCommDescription);

        // Create new community
        Community community = new Community(name.getText().toString(), description.getText().toString());

        // Add to backend
        MainActivity.processor.createCommunity(community);

        createCommunity = findViewById(R.id.createProjectButton);
        createCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCommunity.this, SecondPage.class);
                startActivity(intent);
            }
        });
    }
}
