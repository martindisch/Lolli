package com.martin.lolli;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecyclerFragment extends Fragment {

    public RecyclerView mList;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton mFab;
    private RelativeLayout mRoot, mSnackbar;
    private TextView mSnackButton;

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

        mAdapter = new MyAdapter(getResources().getStringArray(R.array.names), this);
        mList.setAdapter(mAdapter);

        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // Or read size directly from the view's width/height
                int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline.setOval(0, 0, size, size);
            }
        };
        mFab.setOutlineProvider(viewOutlineProvider);

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
        mSnackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSnackbar();
            }
        });

        // Initialize Snackbar
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());
        mSnackbar.setY(mSnackbar.getY() + px);
        mSnackbar.setVisibility(mSnackbar.VISIBLE);

        SharedPreferences sp = getActivity().getSharedPreferences("Lolli", getActivity().MODE_PRIVATE);
        if (sp.getBoolean("firsttime", true)) {
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showSnackbar();
                }
            }, 1000);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("firsttime", false);
            editor.commit();
        }
    }

    private void showSnackbar() {
        float px = -TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());
        moveSnackbar(px);
    }

    private void hideSnackbar() {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());
        moveSnackbar(px);
    }

    private void moveSnackbar(float px) {
        Path p = new Path();

        ObjectAnimator sbAnimator;
        p.moveTo(mFab.getX(), mFab.getY());
        p.rLineTo(0, px);
        sbAnimator = ObjectAnimator.ofFloat(mFab, View.X, View.Y, p);

        ObjectAnimator fabAnimator;
        p.reset();
        p.moveTo(mSnackbar.getX(), mSnackbar.getY());
        p.rLineTo(0, px);
        fabAnimator = ObjectAnimator.ofFloat(mSnackbar, View.X, View.Y, p);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(fabAnimator, sbAnimator);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);
        mList = (RecyclerView) v.findViewById(R.id.rvList);
        mFab = (ImageButton) v.findViewById(R.id.fab);
        mRoot = (RelativeLayout) v.findViewById(R.id.frRoot);
        mSnackButton = (TextView) v.findViewById(R.id.snackbar_button);
        mSnackbar = (RelativeLayout) v.findViewById(R.id.snackbar);
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
            case R.id.firstTime:
                SharedPreferences sps = getActivity().getSharedPreferences("Lolli", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editors = sps.edit();
                editors.putBoolean("firsttime", true);
                editors.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
