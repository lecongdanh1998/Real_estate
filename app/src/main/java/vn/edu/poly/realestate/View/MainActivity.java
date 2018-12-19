package vn.edu.poly.realestate.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Presenter.PresenterMain.PresenterMain;
import vn.edu.poly.realestate.Presenter.PresenterMain.PresenterReponsetoViewMain;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.DetailsMain.DetailsMainActivity;
import vn.edu.poly.realestate.View.User.WalletActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, PresenterReponsetoViewMain {
    ListView listView;
    String screen;
    ImageView img_wallet_MainActivity, img_question_Mainactivity;
    PresenterMain presenterMain;
    int positionListview;
    private ShimmerFrameLayout mShimmerViewContainer, mShimmerViewContainer1;
    int statusInternet;
    private Handler handler;
    private final int delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
        initSharedPre();
        initData();
        initOnClick();
    }

    Runnable runnable = new Runnable() {
        public void run() {
            if (statusInternet == 1) {
                handler.removeCallbacks(runnable);
            } else if (statusInternet == 0) {
                onStart();
                handler.postDelayed(this, delay);
            }
        }
    };

    private void initSharedPre() {
        screen = dataLoginScreen.getString("ScreenMain", "");


    }

    @Override
    protected void onStart() {
        super.onStart();
        statusInternet = dataInternet.getInt("status", 0);
        if (statusInternet == 1) {
            presenterMain.ReceivedHanleData();
            handler.removeCallbacks(runnable);
        } else if (statusInternet == 0) {
            handler.postDelayed(runnable, delay);
        }
    }

    @SuppressLint("NewApi")
    private void initOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Data contructor
                        = (Data) parent.getItemAtPosition(position);
                presenterMain.IntentData(contructor, position);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                positionListview = firstVisibleItem;
            }
        });

        img_wallet_MainActivity.setOnClickListener(this);
        img_question_Mainactivity.setOnClickListener(this);


    }

    private void intentView(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
        if (screen.toString().equals("1")) {

        } else {

        }
        finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
    }

    private void initControl() {
        mShimmerViewContainer1 = findViewById(R.id.shimmer_view_container1);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        img_question_Mainactivity = findViewById(R.id.img_question_mainActivity);
        listView = findViewById(R.id.lst_MainActivity);
        img_wallet_MainActivity = findViewById(R.id.img_wallet_MainActivity);
    }

    private void initData() {
        presenterMain = new PresenterMain(this, this, this);
        handler = new Handler();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer1.startShimmer();
        mShimmerViewContainer.startShimmer();
        if (statusInternet == 1) {
            handler.removeCallbacks(runnable);
        } else if (statusInternet == 0) {
            handler.postDelayed(runnable, delay);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer1.stopShimmer();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onBackPressed() {
        presenterMain.Exit(positionListview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_wallet_MainActivity:
                presenterMain.initButtonIntent(1);
                break;
            case R.id.img_question_mainActivity:
                presenterMain.ShowDialogHelp();
                break;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onFecthDataAdapter(ListViewMainActivityAdapter adapter) {
        positionListview = dataLoginInfo.getInt("position", 3);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransitionoad);
        listView.startAnimation(animation);
        listView.setAdapter(adapter);
        listView.setSelection(positionListview);
        adapter.notifyDataSetChanged();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        mShimmerViewContainer1.stopShimmer();
        mShimmerViewContainer1.setVisibility(View.GONE);
    }

    @Override
    public void onIntentData() {
        intentView(DetailsMainActivity.class);
    }

    @Override
    public void onFetchLogout() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void onShowDialogHelp() {

    }

    @Override
    public void onButtonIntent() {

    }


}
