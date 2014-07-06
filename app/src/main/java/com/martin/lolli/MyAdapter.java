package com.martin.lolli;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mNames;
    private char[] mIcons;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = (TextView) itemView.findViewById(R.id.tvPic);
            mName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    public MyAdapter(String[] names) {
        this.mNames = names;
        for (int i = 0; i < names.length; i++) {
            mIcons[i] = names[i].charAt(0);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mName.setText(mNames[i]);
        viewHolder.mIcon.setText(mIcons[i]);
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }
}
