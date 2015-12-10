package kr.applepi.coolapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;
import kr.applepi.coolapp.R;
import kr.applepi.coolapp.fragments.TodayCardsFragment;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int TAB_TODAY_CARDS = 1;
    private final int TAB_MARKER = 2;
    private final int TAB_ALL_CARDS = 3;
    private final int TAB_CHAT = 4;
    private final int TAB_SQUARE = 5;

    private int state = 1;

    private Retrofit githubController;

    private LinearLayout btnTabTodayCards, btnTabMarker, btnTabAllCards, btnTabChat, btnTabSquare;
    private ImageView ivTodayCards, ivMarker, ivAllCards, ivChat, ivSquare;
    private TextView tvTodayCards, tvMarker, tvAllCards, tvChat, tvSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        initialize();

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        githubController = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .build();
        TodayCardsFragment todayCardsFragment = TodayCardsFragment.newInstance("", "");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, todayCardsFragment);
        transaction.commit();

    }

    private void initialize() {
        btnTabTodayCards = (LinearLayout) findViewById(R.id.btn_tab_today_cards);
        btnTabMarker = (LinearLayout) findViewById(R.id.btn_tab_marker);
        btnTabAllCards = (LinearLayout) findViewById(R.id.btn_tab_all_cards);
        btnTabChat = (LinearLayout) findViewById(R.id.btn_tab_chat);
        btnTabSquare = (LinearLayout) findViewById(R.id.btn_tab_square);

        ivTodayCards = (ImageView) findViewById(R.id.iv_today_cards);
        ivMarker = (ImageView) findViewById(R.id.iv_marker);
        ivAllCards = (ImageView) findViewById(R.id.iv_all_cards);
        ivChat = (ImageView) findViewById(R.id.iv_chat);
        ivSquare = (ImageView) findViewById(R.id.iv_square);

        tvTodayCards = (TextView) findViewById(R.id.tv_today_cards);
        tvMarker = (TextView) findViewById(R.id.tv_marker);
        tvAllCards = (TextView) findViewById(R.id.tv_all_cards);
        tvChat = (TextView) findViewById(R.id.tv_chat);
        tvSquare = (TextView) findViewById(R.id.tv_square);

        btnTabTodayCards.setOnClickListener(this);
        btnTabMarker.setOnClickListener(this);
        btnTabAllCards.setOnClickListener(this);
        btnTabChat.setOnClickListener(this);
        btnTabSquare.setOnClickListener(this);

        setSelected(state);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_my_info) {
            return true;
        } else if(id == R.id.action_store) {
            return true;
        } else if(id == R.id.action_notification) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int beforeState = state;
        setDefault(state);
        switch (view.getId()) {
            case R.id.btn_tab_today_cards:
                state = TAB_TODAY_CARDS;
                break;
            case R.id.btn_tab_marker:
                state = TAB_MARKER;
                break;
            case R.id.btn_tab_all_cards:
                state = TAB_ALL_CARDS;
                break;
            case R.id.btn_tab_chat:
                state = TAB_CHAT;
                break;
            case R.id.btn_tab_square:
                state = TAB_SQUARE;
                break;
            default:
                break;
        }

        if(beforeState == state) {
            return ;
        }
        setSelected(state);
    }

    private void setDefault(int id) {
        switch (id) {
            case TAB_TODAY_CARDS:
                setImage(ivTodayCards, R.drawable.ic_todaycards);
                setTextColor(tvTodayCards, "#898989");
                break;
            case TAB_MARKER:
                setImage(ivMarker, R.drawable.ic_marker);
                setTextColor(tvMarker, "#898989");
                break;
            case TAB_ALL_CARDS:
                setImage(ivAllCards, R.drawable.ic_allcards);
                setTextColor(tvAllCards, "#898989");
                break;
            case TAB_CHAT:
                setImage(ivChat, R.drawable.ic_chat);
                setTextColor(tvChat, "#898989");
                break;
            case TAB_SQUARE:
                setImage(ivSquare, R.drawable.ic_square);
                setTextColor(tvSquare, "#898989");
                break;
        }
    }

    private void setSelected(int id) {

        switch (id) {
            case TAB_TODAY_CARDS:
                setImage(ivTodayCards, R.drawable.ic_todaycards_sel);
                setTextColor(tvTodayCards, R.color.sky);
                break;
            case TAB_MARKER:
                setImage(ivMarker, R.drawable.ic_marker_sel);
                setTextColor(tvMarker, R.color.sky);
                break;
            case TAB_ALL_CARDS:
                setImage(ivAllCards, R.drawable.ic_allcards_sel);
                setTextColor(tvAllCards, R.color.sky);
                break;
            case TAB_CHAT:
                setImage(ivChat, R.drawable.ic_chat_sel);
                setTextColor(tvChat, R.color.sky);
                break;
            case TAB_SQUARE:
                setImage(ivSquare, R.drawable.ic_square_sel);
                setTextColor(tvSquare, R.color.sky);
                break;
        }
    }

    private void setImage(ImageView iv, int id) {
        iv.setImageResource(id);
    }

    private void setTextColor(TextView tv, String color) {
        tv.setTextColor(Color.parseColor(color));
    }

    private void setTextColor(TextView tv, int color) {
        tv.setTextColor(ContextCompat.getColor(this, color));
    }

}
