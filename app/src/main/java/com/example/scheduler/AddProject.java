package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class AddProject extends AppCompatActivity {
    private EditText name;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        // Name and Description
        name = findViewById(R.id.newProjectName);
        description = findViewById(R.id.newProjectDescription);

        // Create new project
    }
}
