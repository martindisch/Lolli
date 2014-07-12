package com.martin.lolli;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RecyclerFragment extends Fragment {

    private RecyclerView mList;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton mFab;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    public static RecyclerFragment newInstance() {
        RecyclerFragment fragment = new RecyclerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mList.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(getResources().getStringArray(R.array.names));
        mList.setAdapter(mAdapter);

        SharedPreferences sp = getActivity().getSharedPreferences("Lolli", getActivity().MODE_PRIVATE);
        if (sp.getBoolean("firsttime", true)) {
            Toast.makeText(getActivity(), "Long press to remove an object", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("firsttime", false);
            editor.commit();
        }

        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
        mFab.setOutline(outline);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialog = inflater.inflate(R.layout.dialog_add, null);
                final EditText name = (EditText) dialog.findViewById(R.id.etName);
                final EditText position = (EditText) dialog.findViewById(R.id.etPosition);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add");
                builder.setView(dialog);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.add(name.getText().toString(), Integer.parseInt(position.getText().toString()));
                    }

                });
                builder.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);
        mList = (RecyclerView) v.findViewById(R.id.rvList);
        mFab = (ImageButton) v.findViewById(R.id.fab);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recycler_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theme:
                SharedPreferences sp = getActivity().getSharedPreferences("Lolli", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                if (sp.getInt("Theme", R.style.Orange) == R.style.Orange) {
                    editor.putInt("Theme", R.style.Green);
                } else {
                    editor.putInt("Theme", R.style.Orange);
                }
                editor.commit();
                getActivity().recreate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
