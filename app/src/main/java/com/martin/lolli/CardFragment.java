package com.martin.lolli;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class CardFragment extends Fragment {

    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MyActivity) getActivity()).updateTitle(2);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.card_fragment, menu);
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
