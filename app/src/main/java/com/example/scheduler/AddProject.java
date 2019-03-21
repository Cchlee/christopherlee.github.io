package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import java.util.Map;
import java.util.HashMap;
import data.*;

public class AddProject extends AppCompatActivity {
    private EditText name;
    private EditText description;

    private Button addRole;
    private Map<String, Integer> roles;
    private Map<Integer, Integer> ids;

    private Button createProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        // Name and Description
        name = findViewById(R.id.newProjectName);
        description = findViewById(R.id.newProjectDescription);

        // Get community name
        Intent i = getIntent();
        final String communityName = i.getStringExtra("Community name");

        // Add roles
        roles = new HashMap<>();
        ids = new HashMap<>();

        addRole = findViewById(R.id.addRoleButton);
        addRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRole();
            }
        });

        // Create new project button
        createProject = findViewById(R.id.createProjectButton);
        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProject();

                // create Project object and pass to View Community
                Project project = new Project(name.toString(), description.toString(), communityName, roles);
                Intent intent = new Intent(AddProject.this, ViewCommunity.class);
                intent.putExtra("Project", project);
                startActivity(intent);
            }
        });

    }

    private void createProject() {
        for (Map.Entry<Integer, Integer> item : ids.entrySet()) {
            EditText role = findViewById(item.getKey());
            EditText roleNum = findViewById(item.getValue());

            roles.put(role.getText().toString(), Integer.parseInt(roleNum.getText().toString()));
        }
    }

    private void addRole() {
        // retrieve parent layout
        LinearLayout parent = findViewById(R.id.parentLL);

        // add new layout
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        // add EditText for role
        EditText et1 = new EditText(this);
        //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //et1.setLayoutParams(p);
        et1.setHint("Enter new role");
        et1.setWidth(150);
        et1.setHeight(130);
        et1.setId(View.generateViewId());

        // add EditText for number of roles
        EditText et2 = new EditText(this);
        //et2.setLayoutParams(p);
        et2.setHint("Enter number of roles");
        et2.setWidth(150);
        et2.setHeight(130);
        et2.setId(View.generateViewId());

        // add to ids map
        ids.put(et1.getId(), et2.getId());

        // add 2 EditTexts to new layout
        ll.addView(et1);
        ll.addView(et2);

        // add new layout to parent
        parent.addView(ll);
    }
}
