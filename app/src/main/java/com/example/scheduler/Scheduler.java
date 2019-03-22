package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Scheduler extends AppCompatActivity {

    // I don't like git
    private String fullSchedule;
    private TextView outputSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

        fullSchedule = getIntent().getStringExtra("outputSched");

        outputSchedule = findViewById(R.id.outputtedSched);
        outputSchedule.setText(fullSchedule);
    }
}
