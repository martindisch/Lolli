package com.martin.lolli;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> mNames;
    private String[] mIcons;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = (TextView) itemView.findViewById(R.id.tvPic);
            mName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    public MyAdapter(String[] names) {
        mNames = new ArrayList<String>();
        Collections.addAll(mNames, names);
        createIcons();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mName.setText(mNames.get(i));
        viewHolder.mIcon.setText(mIcons[i]);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public void add(String name, int position) {
        mNames.add(position, name);
        createIcons();
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mNames.remove(position);
        createIcons();
        notifyItemRemoved(position);
    }

    public void edit(String name, int position) {
        mNames.set(position, name);
        createIcons();
        notifyItemChanged(position);
    }

    private void createIcons() {
        mIcons = new String[mNames.size()];
        for (int i = 0; i < mNames.size(); i++) {
            mIcons[i] = mNames.get(i).charAt(0) + "";
        }
    }
}
