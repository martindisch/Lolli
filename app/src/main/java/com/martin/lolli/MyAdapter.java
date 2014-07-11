package com.martin.lolli;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> mNames;
    private String[] mIcons;

    public MyAdapter(String[] names) {
        mNames = new ArrayList<String>();
        Collections.addAll(mNames, names);
        createIcons();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);
        v.setOnClickListener(new RecyclerView.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialog = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_rename, null);
                final EditText name = (EditText) dialog.findViewById(R.id.etName);
                final int position = (Integer) view.findViewById(R.id.tvName).getTag();
                name.setText(mNames.get(position));
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Rename");
                builder.setView(dialog);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Rename", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit(name.getText().toString(), position);
                    }

                });
                builder.show();
            }
        });
        v.setOnLongClickListener(new RecyclerView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final int position = (Integer) view.findViewById(R.id.tvName).getTag();
                remove(position);
                return true;
            }
        });
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mName.setText(mNames.get(i));
        viewHolder.mIcon.setText(mIcons[i]);
        viewHolder.mName.setTag(i);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = (TextView) itemView.findViewById(R.id.tvPic);
            mName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
