package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class SecondPage extends AppCompatActivity {
    private Button newCommunity;
    private Button searchCommunity;
    private String[] displayCommunities;
    private boolean isUser;

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

        isUser = true;
        searchCommunity = findViewById(R.id.searchCommunityButton);
        searchCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondPage.this, SearchActivity.class); //SHOULD LINK TO SEARCH PAGE
                intent.putExtra("isUser", isUser);
                startActivity(intent);
            }
        });

        displayCommunities = new String[5];
        displayCommunities[0] = "UNICEF";
        displayCommunities[1] = "WWF";
        displayCommunities[2] = "OXFAM";
        displayCommunities[3] = "ACS";
        displayCommunities[4] = "RAINN";

        LinearLayout recentCommunities = findViewById(R.id.recentCommunities);

        LayoutInflater inflater = LayoutInflater.from(this);

        for(int i = 0; i < displayCommunities.length; i++) {

            View view = inflater.inflate(R.layout.layout_for_scroller, recentCommunities, false);

            TextView textView = view.findViewById(R.id.rctext);
            textView.setText(displayCommunities[i]);

            ImageView imageView = view.findViewById(R.id.communityScrollerImage);
            imageView.setImageResource(R.mipmap.ic_launcher);

            recentCommunities.addView(view);

        }

    }
}
