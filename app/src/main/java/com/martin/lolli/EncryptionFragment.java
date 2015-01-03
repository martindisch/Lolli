package com.martin.lolli;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static com.tozny.crypto.android.AesCbcWithIntegrity.decryptString;
import static com.tozny.crypto.android.AesCbcWithIntegrity.encrypt;
import static com.tozny.crypto.android.AesCbcWithIntegrity.generateKeyFromPassword;
import static com.tozny.crypto.android.AesCbcWithIntegrity.generateSalt;
import static com.tozny.crypto.android.AesCbcWithIntegrity.saltString;

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
                fencrypt();
            }
        });
        mDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fdecrypt();
            }
        });
        mGenSalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fsalt();
            }
        });

        Log.d("FFF", "OnCreateView");
        if (savedInstanceState != null) {
            mText.setText(savedInstanceState.getString("text"));
            mPassword.setText(savedInstanceState.getString("password"));
            mSalt.setText(savedInstanceState.getString("salt"));
            mOutput.setText(savedInstanceState.getString("output"));
        }
        return layout;
    }

    private void fencrypt() {
        if (!mPassword.getText().toString().contentEquals("") && !mSalt.getText().toString().contentEquals("") && !mText.getText().toString().contentEquals("")) {
            try {
                AesCbcWithIntegrity.SecretKeys key = generateKeyFromPassword(mPassword.getText().toString(), mSalt.getText().toString());
                AesCbcWithIntegrity.CipherTextIvMac civ = encrypt(mText.getText().toString(), key);
                mOutput.setText(civ.toString());
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void fdecrypt() {
        if (!mPassword.getText().toString().contentEquals("") && !mSalt.getText().toString().contentEquals("") && !mText.getText().toString().contentEquals("")) {
            try {
                AesCbcWithIntegrity.SecretKeys key = generateKeyFromPassword(mPassword.getText().toString(), mSalt.getText().toString());
                String plainText = decryptString(new AesCbcWithIntegrity.CipherTextIvMac(mText.getText().toString()), key);
                mOutput.setText(plainText);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void fsalt() {
        try {
            String salt = saltString(generateSalt());
            mSalt.setText(salt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", mText.getText().toString());
        outState.putString("password", mPassword.getText().toString());
        outState.putString("salt", mSalt.getText().toString());
        outState.putString("output", mOutput.getText().toString());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MyActivity) getActivity()).updateTitle(4);
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
