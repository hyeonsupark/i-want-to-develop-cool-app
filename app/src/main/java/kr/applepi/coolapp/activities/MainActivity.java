package kr.applepi.coolapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int TAB_TODAY_CARDS = 1;
    private final int TAB_MARKER = 2;
    private final int TAB_ALL_CARDS = 3;
    private final int TAB_CHAT = 4;
    private final int TAB_SQUARE = 5;

    private int currentState = 1;

    private Retrofit githubController;

    private LinearLayout btnTabTodayCards, btnTabMarker, btnTabAllCards, btnTabChat, btnTabSquare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        TodayCardsFragment todayCardsFragment = TodayCardsFragment.newInstance("","");

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_tab_today_cards:

                break;
            case R.id.btn_tab_marker:
                break;
            case R.id.btn_tab_all_cards:
                break;
            case R.id.btn_tab_chat:
                break;
            case R.id.btn_tab_square:
                break;
            default:
                break;
        }
    }
}
