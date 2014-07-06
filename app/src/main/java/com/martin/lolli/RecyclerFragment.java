package com.martin.lolli;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerFragment extends Fragment {

    private RecyclerView mList;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        mLayoutManager = new LinearLayoutManager(getActivity());
        mList.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(getResources().getStringArray(R.array.names));
        mList.setAdapter(mAdapter);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);
        mList = (RecyclerView) v.findViewById(R.id.rvList);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.recycler_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                mAdapter.add("Hello", 3);
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View ttDialog = ttinflater.inflate(R.layout.ttdialog);
                final Spinner ttsYear = (Spinner) ttDialog.findViewById(R.id.ttsYear);
                final EditText ttetClass = (EditText) ttDialog.findViewById(R.id.ttetClass);
                SharedPreferences sp = getApplicationContext().getSharedPreferences("Kantidroid", Context.MODE_PRIVATE);
                ttsYear.setSelection(sp.getInt("yearindex", 0));
                ttetClass.setText(sp.getString("class", ""));
                AlertDialog.Builder ttdg = new AlertDialog.Builder(this);
                ttdg.setTitle("Stundenplan");
                ttdg.setView(ttDialog);
                ttdg.setNegativeButton("Abbrechen", null);
                ttdg.setPositiveButton("Ansehen", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                ttdg.show();
                break;
            case R.id.remove:
                mAdapter.remove(3);
                break;
            case R.id.edit:
                mAdapter.edit("Nooo", 3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
