package com.martin.lolli;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("Lolli", MODE_PRIVATE);
        int theme = sp.getInt("Theme", R.style.Orange);
        setTheme(theme);
        setContentView(R.layout.activity_detail);
    }
}
