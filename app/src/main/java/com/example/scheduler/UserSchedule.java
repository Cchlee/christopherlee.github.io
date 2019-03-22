package com.example.scheduler;
import android.view.View;
import data.User;
import java.util.Set;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

public class UserSchedule extends AppCompatActivity {

    private static int[] timeslotIDs = {R.id.timeslot1, R.id.timeslot2, R.id.timeslot3, R.id.timeslot4,
            R.id.timeslot5, R.id.timeslot6, R.id.timeslot7, R.id.timeslot8, R.id.timeslot9, R.id.timeslot10,
            R.id.timeslot11, R.id.timeslot12, R.id.timeslot13, R.id.timeslot14,
            R.id.timeslot15, R.id.timeslot16, R.id.timeslot17, R.id.timeslot18, R.id.timeslot19, R.id.timeslot20,
            R.id.timeslot21, R.id.timeslot22, R.id.timeslot23, R.id.timeslot24,
            R.id.timeslot25, R.id.timeslot26, R.id.timeslot27, R.id.timeslot28, R.id.timeslot29, R.id.timeslot30,
            R.id.timeslot31, R.id.timeslot32, R.id.timeslot33, R.id.timeslot34,
            R.id.timeslot35, R.id.timeslot36, R.id.timeslot37, R.id.timeslot38, R.id.timeslot39, R.id.timeslot40,
            R.id.timeslot41, R.id.timeslot42, R.id.timeslot43, R.id.timeslot44,
            R.id.timeslot45, R.id.timeslot46, R.id.timeslot47, R.id.timeslot48, R.id.timeslot49, R.id.timeslot50,
            R.id.timeslot51, R.id.timeslot52, R.id.timeslot53, R.id.timeslot54,
            R.id.timeslot55, R.id.timeslot56, R.id.timeslot57, R.id.timeslot58, R.id.timeslot59, R.id.timeslot60,
            R.id.timeslot61, R.id.timeslot62, R.id.timeslot63, R.id.timeslot64,
            R.id.timeslot65, R.id.timeslot66, R.id.timeslot67, R.id.timeslot68, R.id.timeslot69, R.id.timeslot70};

    private static Button[] buttons = new Button[timeslotIDs.length];

    private boolean[] schedule = new boolean[168];
    private boolean[] pressed = new boolean[timeslotIDs.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);

        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");

        for (int i = 0; i < 168; i++) {
            schedule[i] = false;
        }

        for (int i = 0; i < timeslotIDs.length; i++) {
            final Button currButton = (Button) findViewById(timeslotIDs[i]);
            buttons[i] = currButton;
            final int buttonId = i;

            currButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeStatus(currButton, buttonId);
                }
            });
        }

        Button makeSchedule = (Button) findViewById(R.id.buttonAddSchedule);

        makeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSchedule.this, SecondPage.class); //SHOULD LINK TO SEARCH PAGE
                Set<User> users = MainActivity.processor.getUsers();
                for (User user : users) {
                    if (user.getDisplayName() == username) {
                        user.setSchedule(schedule);
                    }
                }
                startActivity(intent);
            }
        });



    }

    public void changeStatus(Button currButton, int i) {
        if (pressed[i]) {
            currButton.setSelected(false);
            pressed[i] = false;
            int scheduleId = (i % 10) + 9;
            schedule[scheduleId] = false;

        } else {
            currButton.setSelected(true);
            pressed[i] = true;
            int scheduleId = (i % 10) + 9;
            schedule[scheduleId] = true;
        }
    }
}
