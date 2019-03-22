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
    private ImageView communityIcon;
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

        communityIcon = findViewById(R.id.communityIcon);
        communityIcon.setImageResource(R.drawable.ic_public_black);

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
        userNames[0] = "Andrew Aardvark";
        userNames[1] = "Ben Bear";
        userNames[2] = "Carrie Carp";
        userNames[3] = "Dennis Deer";
        userNames[4] = "Elizabeth Eagle";
        userNames[5] = "Fred Fish";
        userNames[6] = "George Goat";
        userNames[7] = "Harry Horse";
        userNames[8] = "Ian Iguana";
        userNames[9] = "Jenny Jaguar";

        LinearLayout usersInCommunity = findViewById(R.id.usersInCommunity);

        LayoutInflater inflater = LayoutInflater.from(this);

        for(int i = 0; i < userNames.length; i++) {

            View view = inflater.inflate(R.layout.layout_for_vertical_scroller, usersInCommunity, false);

            TextView textView = view.findViewById(R.id.verticalScrollText);
            textView.setText(userNames[i]);

            usersInCommunity.addView(view);

        }

        LinearLayout projectsInCommunity = findViewById(R.id.projectsInCommunity);

        LayoutInflater inflaterProjects = LayoutInflater.from(this);

        for(int i = 0; i < userNames.length; i++) {

            View view = inflaterProjects.inflate(R.layout.layout_for_vertical_scroller, projectsInCommunity, false);

            TextView textView = view.findViewById(R.id.verticalScrollText);
            textView.setText(userNames[i]);


            projectsInCommunity.addView(view);

        }
    }
}
