package com.martin.lolli;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class WidgetFragment extends Fragment {

    private Button mButton, mVisibility;
    private boolean mVisible = false;
    private RadioGroup mGroup;
    private FrameLayout mHero;
    private int mElevation = 0;
    private RelativeLayout mViews;

    public WidgetFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WidgetFragment newInstance() {
        WidgetFragment fragment = new WidgetFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mElevation += 10;
                mHero.setTranslationZ(mElevation);
            }
        });
        mVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mVisible) {
                    hide();

                } else {
                    reveal();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_widget, container, false);
        mButton = (Button) rootView.findViewById(R.id.bGo);
        mVisibility = (Button) rootView.findViewById(R.id.bReveal);
        mGroup = (RadioGroup) rootView.findViewById(R.id.rgRadios);
        mHero = (FrameLayout) rootView.findViewById(R.id.frHero);
        mViews = (RelativeLayout) rootView.findViewById(R.id.rlViews);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.widget_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theme:
                SharedPreferences sp = getActivity().getPreferences(getActivity().MODE_PRIVATE);
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

    private void hide() {
        // get the center for the clipping circle
        int cx = mViews.getLeft();
        int cy = mViews.getTop();

        // get the initial radius for the clipping circle
        int initialRadius = mViews.getWidth();

        // create the animation (the final radius is zero)
        ValueAnimator anim =
                ViewAnimationUtils.createCircularReveal(mViews, cx, cy, initialRadius, 0);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mViews.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();
        mVisible = false;
    }

    private void reveal() {
        // get the center for the clipping circle
        int cx = mViews.getLeft();
        int cy = mViews.getTop();

        // get the final radius for the clipping circle
        int finalRadius = mViews.getWidth();

        // create and start the animator for this view
        // (the start radius is zero)
        ValueAnimator anim =
                ViewAnimationUtils.createCircularReveal(mViews, cx, cy, 0, finalRadius);

        // make the view visible when the animation starts
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mViews.setVisibility(View.VISIBLE);
            }
        });
        anim.start();
        mVisible = true;
    }

}
