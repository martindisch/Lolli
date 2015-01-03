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
import android.widget.Button;
import android.widget.EditText;

public class EncryptionFragment extends Fragment {

    private EditText mText, mPassword, mSalt, mOutput;
    private Button mEncrypt, mDecrypt, mGenSalt;

    public EncryptionFragment() {
        // Required empty public constructor
    }

    public static EncryptionFragment newInstance() {
        EncryptionFragment fragment = new EncryptionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_encryption, container, false);

        mText = (EditText) layout.findViewById(R.id.etText);
        mPassword = (EditText) layout.findViewById(R.id.etPassword);
        mSalt = (EditText) layout.findViewById(R.id.etSalt);
        mOutput = (EditText) layout.findViewById(R.id.etOutput);
        mEncrypt = (Button) layout.findViewById(R.id.bEncrypt);
        mDecrypt = (Button) layout.findViewById(R.id.bDecrypt);
        mGenSalt = (Button) layout.findViewById(R.id.bSalt);

        mEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encrypt();
            }
        });
        mDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrypt();
            }
        });
        mGenSalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salt();
            }
        });
        return layout;
    }

    private void encrypt() {

    }
    private void decrypt() {

    }
    private void salt() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.encryption_fragment, menu);
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
