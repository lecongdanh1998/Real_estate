package vn.edu.poly.realestate.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
    Toolbar toolbar;
    String screen;
    ImageView img_wallet_MainActivity;
    PresenterMain presenterMain;
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

    private void initOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewMainActivityContructor contructor
                        = (ListViewMainActivityContructor) parent.getItemAtPosition(position);
                presenterMain.IntentData(contructor);
            }
        });
        img_wallet_MainActivity.setOnClickListener(this);
    }

    private void intentView(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
        if (screen.toString().equals("1")) {
            finish();
        } else {

        }
        overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
    }

    private void initControl() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = findViewById(R.id.lst_MainActivity);
        img_wallet_MainActivity = findViewById(R.id.img_wallet_MainActivity);
    }

    private void initData() {
        setSupportActionBar(toolbar);
        presenterMain = new PresenterMain(this,this);
        presenterMain.ReceivedHanleData();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                Toast.makeText(this, "" + getResources().getString(R.string.txt_help), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_languae:
                Toast.makeText(this, "" + getResources().getString(R.string.txt_langueage), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "" + getResources().getString(R.string.txt_setting), Toast.LENGTH_SHORT).show();
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_wallet_MainActivity:
                intentView(WalletActivity.class);
                break;
        }
    }

    @Override
    public void onFecthDataAdapter(ListViewMainActivityAdapter adapter) {
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onIntentData() {
        intentView(DetailsMainActivity.class);
    }


}
