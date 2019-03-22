package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import data.*;
import java.util.*;

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
        final Community community = new Community(name.getText().toString(), description.getText().toString());
        createCommunity = findViewById(R.id.createCommunityButton);
        createCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add to backend
                MainActivity.processor.createCommunity(community);
//                System.out.println("I add community");
//                List<Community> communities = MainActivity.processor.getCommunities();
//                for (Community c : communities) {
//                    System.out.println(c.getName());
//                }


                Intent intent = new Intent(CreateCommunity.this, SecondPage.class);
                startActivity(intent);
            }
        });
    }
}
