package com.example.scheduler;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import java.util.Map;

import data.*;

public class ViewCommunity extends AppCompatActivity {
    private Button newProject;
    private Button runScheduler;
    private String communityName;
    private boolean projectVisibility;
    private String[] userNames;
    private ImageView communityIcon;
    private Community community;
    private TextView title;
    private Map<String, Community> communityMap;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_community);

        communityName = getIntent().getStringExtra("Clicked Community");
        communityMap = MainActivity.processor.getCommunitiesMap();
        community = communityMap.get(communityName);
        //community = getIntent().getParcelableExtra("Clicked Community");

        title = findViewById(R.id.Title);
        title.setText(community.getName());

        description = findViewById(R.id.commDescript);
        description.setText(community.getDescription());


        community.setImageResource();
        communityIcon = findViewById(R.id.communityIcon);
        communityIcon.setImageResource(community.getImageResource());

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
