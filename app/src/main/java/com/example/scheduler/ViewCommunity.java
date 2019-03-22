package com.example.scheduler;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import data.*;
import processor.Algorithm;

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
    private String scheduleOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_community);

        final Community c = new Community("WPS", "swinging");
        CommunityMember s = new CommunityMember("Stef", "12345");
        CommunityMember t = new CommunityMember("Tasya", "12345");
        CommunityMember k = new CommunityMember("Keri", "12345");
        CommunityMember m = new CommunityMember("Maddie", "12345");
        s.addPreference("dancer", 2);
        t.addPreference("dancer", 2);
        k.addPreference("dancer", 1);
        m.addPreference("tech", 1);
        m.addPreference("dancer", 1);

        boolean[] scheduleS = new boolean[24*7];
        boolean[] scheduleT = new boolean[24*7];
        boolean[] scheduleK = new boolean[24*7];
        boolean[] scheduleM = new boolean[24*7];
        for (int i=20; i<30; i++)
        {
            scheduleS[i] = true;
            scheduleM[i] = true;
            scheduleT[i] = true;
            scheduleK[i] = true;
        }
        s.setSchedule(scheduleS);
        t.setSchedule(scheduleK);
        k.setSchedule(scheduleT);
        m.setSchedule(scheduleM);

        Map<String, Integer> jazzRoles = new TreeMap<String, Integer>();
        jazzRoles.put("dancer", 3);

        Map<String, Integer> elecRoles = new TreeMap<String, Integer>();
        elecRoles.put("dancer", 2);
        elecRoles.put("dancer", 1);
        elecRoles.put("tech", 1);

        Project jazz = new Project("jazz", "jazzy", c.getName(), jazzRoles);
        Project elec = new Project("electric", "electroswing", c.getName(), elecRoles);

        c.addProject(jazz);
        c.addProject(elec);
        HashSet<CommunityMember> peeps = new HashSet<CommunityMember>();
        peeps.add(m);
        peeps.add(s);
        peeps.add(k);
        peeps.add(t);
        c.setMembers(peeps);

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
                Algorithm.runAlgorithm(c);
                scheduleOutput = Algorithm.printSchedule(c);
                Intent intent = new Intent(ViewCommunity.this, Scheduler.class);
                intent.putExtra("outputSched", scheduleOutput);
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
