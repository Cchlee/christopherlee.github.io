package com.example.scheduler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import data.Community;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder> implements Filterable {

    private List<Community> mCommunityList;
    private List<Community> mCommunityListFull;


    public static class CommunityViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.TempID);
            mTextView1 = itemView.findViewById(R.id.AnotherTempID);
            mTextView2 = itemView.findViewById(R.id.AnotherTempID2);

        }
    }

    public CommunityAdapter(List<Community> communityList){
        mCommunityList = communityList;
        mCommunityListFull = new ArrayList<>(communityList);
    }

    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.community_item, viewGroup, false );
        CommunityViewHolder cvh = new CommunityViewHolder(view);
        return cvh;
    }


    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder communityViewHolder, int i) {
        Community current = mCommunityList.get(i);
        communityViewHolder.mImageView.setImageResource(current.getImageResource());
        communityViewHolder.mTextView1.setText(current.getName());
        communityViewHolder.mTextView2.setText(current.getDescription());


    }

    @Override
    public int getItemCount() {
        return mCommunityList.size();
    }

    @Override
    public Filter getFilter() {
        return communityFilter;
    }

    private Filter communityFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Community> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mCommunityListFull);
            }
            else{
                String filteredPattern = constraint.toString().toLowerCase().trim();
                 for (Community c: mCommunityListFull){
                     if (c.getName().toLowerCase().contains(filteredPattern)){
                         filteredList.add(c);
                     }
                 }
            }

            FilterResults filteredResults = new FilterResults();
            filteredResults.values = filteredList;
            return filteredResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mCommunityList.clear();
            mCommunityList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
