package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import data.*;

public class ViewCommunity extends AppCompatActivity {
    private Button newProject;
    private Button runScheduler;
    private String communityName;

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


        // Run scheduler algorithm
        runScheduler = findViewById(R.id.schedulerButton);
        runScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCommunity.this, Scheduler.class);
                startActivity(intent);
            }
        });
    }
}
