package kr.applepi.coolapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.applepi.coolapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayCardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayCardsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayCardsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayCardsFragment newInstance(String param1, String param2) {
        TodayCardsFragment fragment = new TodayCardsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TodayCardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        CardFragment card = CardFragment.newInstance(R.drawable.profile1, "곧3", "좀이따지울거에요", "10대 후반, 서울, 학생", "75%");
        CardFragment card2 = CardFragment.newInstance(R.drawable.profile2, "착함 차분한 편 잘웃음", "뚜뚜루뚜뚜뚣", "20대 초반, 경기, 대학생", "67%");
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_today_card_1, card);
        transaction.replace(R.id.fragment_today_card_2, card2);
        transaction.commit();

        return inflater.inflate(R.layout.fragment_today_cards, container, false);
    }


}
