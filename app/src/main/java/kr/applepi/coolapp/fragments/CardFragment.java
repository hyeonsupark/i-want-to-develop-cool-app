package kr.applepi.coolapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import kr.applepi.coolapp.R;


public class CardFragment extends Fragment {

    private int profile;
    private String message, nickname, info, matchingRate;

    public static CardFragment newInstance(int id, String... params) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("profile", id);
        args.putString("message", params[0]);
        args.putString("nickname", params[1]);
        args.putString("info", params[2]);
        args.putString("matchingRate", params[3]);
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
            profile = getArguments().getInt("profile");
            message = getArguments().getString("message");
            nickname = getArguments().getString("nickname");
            info = getArguments().getString("info");
            matchingRate = getArguments().getString("matchingRate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_card, container, false);
        ImageView ivProfile = (ImageView) parentView.findViewById(R.id.iv_profile);
        TextView tvMessage = (TextView) parentView.findViewById(R.id.tv_message);
        TextView tvNickname = (TextView) parentView.findViewById(R.id.tv_nickname);
        TextView tvInfo = (TextView) parentView.findViewById(R.id.tv_info);
        TextView tvMatchingRate = (TextView) parentView.findViewById(R.id.tv_matching_rate);


        ivProfile.setImageResource(profile);
        tvMessage.setText(message);
        tvNickname.setText(nickname);
        tvInfo.setText(info);
        tvMatchingRate.setText(matchingRate);
        return parentView;
    }

}
