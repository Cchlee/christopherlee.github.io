package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import data.*;

public class ViewCommunity extends AppCompatActivity {
    private Button newProject;
    private Button runScheduler;
    private String communityName;
    private boolean projectVisibility;
    private String[] userNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_community);

        // Add new project
        newProject = findViewById(R.id.addProjectButton);
        newProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCommunity.this, AddProject.class);
                intent.putExtra("Community name", communityName);
                startActivity(intent);
            }
        });

        projectVisibility = getIntent().getBooleanExtra("isUser", true);
        if(projectVisibility) {
            newProject.setVisibility(View.GONE);
        } else {
            newProject.setVisibility(View.VISIBLE);
        }

        // Run scheduler algorithm
        runScheduler = findViewById(R.id.schedulerButton);
        runScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCommunity.this, Scheduler.class);
                startActivity(intent);
            }
        });

        userNames = new String[10];
        userNames[0] = "a";
        userNames[1] = "b";
        userNames[2] = "c";
        userNames[3] = "d";
        userNames[4] = "e";
        userNames[5] = "f";
        userNames[6] = "g";
        userNames[7] = "h";
        userNames[8] = "i";
        userNames[9] = "j";

        LinearLayout usersInCommunity = findViewById(R.id.usersInCommunity);

        LayoutInflater inflater = LayoutInflater.from(this);

        for(int i = 0; i < userNames.length; i++) {

            View view = inflater.inflate(R.layout.layout_for_vertical_scroller, usersInCommunity, false);

            TextView textView = view.findViewById(R.id.verticalScrollText);
            textView.setText(userNames[i]);

            usersInCommunity.addView(view);

        }
    }
}
