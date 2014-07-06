package com.martin.lolli;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class WidgetFragment extends Fragment {

    private Button mButton;
    private RadioGroup mGroup;
    private RelativeLayout mCard;
    private int mElevation = 0;

    // TODO: Rename and change types and number of parameters
    public static WidgetFragment newInstance() {
        WidgetFragment fragment = new WidgetFragment();
        return fragment;
    }
    public WidgetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mElevation += 10;
                mCard.setTranslationZ(mElevation);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_widget, container, false);
        mButton = (Button) rootView.findViewById(R.id.bGo);
        mGroup = (RadioGroup) rootView.findViewById(R.id.rgRadios);
        mCard = (RelativeLayout) rootView.findViewById(R.id.rlCard);
        return rootView;
    }


}
