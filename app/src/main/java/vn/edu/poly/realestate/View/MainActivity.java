package vn.edu.poly.realestate.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.Presenter.PresenterMain.PresenterMain;
import vn.edu.poly.realestate.Presenter.PresenterMain.PresenterReponsetoViewMain;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.DetailsMain.DetailsMainActivity;
import vn.edu.poly.realestate.View.User.WalletActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, PresenterReponsetoViewMain {
    ListView listView;
    String screen;
    ImageView img_wallet_MainActivity,img_question_Mainactivity;
    PresenterMain presenterMain;
    int positionListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
        initData();
        initSharedPre();
        initOnClick();
    }

    private void initSharedPre() {
        screen = dataLoginScreen.getString("ScreenMain", "");
    }

    @SuppressLint("NewApi")
    private void initOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                ListViewMainActivityContructor contructor
                        = (ListViewMainActivityContructor) parent.getItemAtPosition(position);
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
        img_question_Mainactivity = findViewById(R.id.img_question_mainActivity);
        listView = findViewById(R.id.lst_MainActivity);
        img_wallet_MainActivity = findViewById(R.id.img_wallet_MainActivity);
    }

    private void initData() {
        presenterMain = new PresenterMain(this, this, this);
        presenterMain.ReceivedHanleData();

    }

    @Override
    public void onBackPressed() {
        presenterMain.Exit(positionListview);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_wallet_MainActivity:
                onBackPressed();
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
        listView.setAdapter(adapter);
        listView.setSelection(positionListview);
        adapter.notifyDataSetChanged();
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


}
