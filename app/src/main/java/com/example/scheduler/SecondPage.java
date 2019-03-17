package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class SecondPage extends AppCompatActivity {
    private Button newCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        newCommunity = findViewById(R.id.createCommunityButton);
        newCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondPage.this, CreateCommunity.class);
                startActivity(intent);
            }
        });

    }
}
