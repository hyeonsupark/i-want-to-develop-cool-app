package kr.applepi.coolapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.applepi.coolapp.R;


public class CardFragment extends Fragment {

    private String param;

    public static CardFragment newInstance(String param) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        fragment.setArguments(args);
        return fragment;
    }

    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            param = getArguments().getString("param");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_card, container, false);
        TextView tv = (TextView) parentView.findViewById(R.id.info_text);
        tv.setText(param);
        return parentView;
    }

}
