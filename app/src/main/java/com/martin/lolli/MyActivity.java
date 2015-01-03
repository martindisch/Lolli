package com.martin.lolli;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;


public class MyActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("Lolli", MODE_PRIVATE);
        int theme = sp.getInt("Theme", R.style.Orange);
        setTheme(theme);
        setContentView(R.layout.activity_my);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position, boolean fromSavedInstanceState) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        if (!fromSavedInstanceState) {
            switch (position) {
                case 0:
                    fragmentManager.beginTransaction().replace(R.id.container, WidgetFragment.newInstance()).commit();
                    break;
                case 1:
                    fragmentManager.beginTransaction().replace(R.id.container, CardFragment.newInstance()).commit();
                    break;
                case 2:
                    fragmentManager.beginTransaction().replace(R.id.container, RecyclerFragment.newInstance()).commit();
                    break;
                case 3:
                    fragmentManager.beginTransaction().replace(R.id.container, EncryptionFragment.newInstance()).commit();
            }
        }
    }

    public void updateTitle(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
        }
        setTitle(mTitle);
    }

}
