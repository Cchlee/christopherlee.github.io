package com.example.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import data.Community;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CommunityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        System.out.println("SEARCH GET COMMUNITY: " + MainActivity.processor.getCommunities().size());
//        List<Community> communities = MainActivity.processor.getCommunities();
//        for (Community c : communities) {
//            System.out.println(c.getName());
//        }

        final List<Community> communityList = MainActivity.processor.getCommunities();
        mRecyclerView = findViewById(R.id.recyclerView);
        //Uncomment line 27 if you think Communities will have fixed number. Doubt it tho. But will increase performance.
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CommunityAdapter(communityList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnCommunityClickListener(new CommunityAdapter.onCommunityClickListener() {
            @Override
            public void onCommunityClick(int position) {
                 Community clicked = communityList.get(position);
                 Intent intent = new Intent(SearchActivity.this, ViewCommunity.class);
                 intent.putExtra("Clicked Community", clicked.getName());
                 startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.community_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_community);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

}
