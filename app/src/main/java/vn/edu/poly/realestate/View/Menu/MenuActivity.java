package vn.edu.poly.realestate.View.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterMenu.PresenterMenu;
import vn.edu.poly.realestate.Presenter.PresenterMenu.PresenterReponsetoViewMenu;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.Franchise.FranchiseActivity;
import vn.edu.poly.realestate.View.InfoAccount.InfoaccountActivity;
import vn.edu.poly.realestate.View.RealEstateInvestments.RealEstateInvestmentsActivity;

public class MenuActivity extends BaseActivity implements View.OnClickListener, PresenterReponsetoViewMenu {
    LinearLayout LNL_user, LNT_history, LNT_dangtinbatdongsan_menu, LNT_danhgiaduan_menu;
    PresenterMenu presenterMenu;
    Toolbar toolbar;
    TableRow TBR_nguoidangtinbatdongsan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        LNT_dangtinbatdongsan_menu.setOnClickListener(this);
        LNL_user.setOnClickListener(this);
        LNT_history.setOnClickListener(this);
        LNT_danhgiaduan_menu.setOnClickListener(this);
    }

    private void initData() {
        setSupportActionBar(toolbar);
        presenterMenu = new PresenterMenu(this, this);
        presenterMenu.setVisibility(TBR_nguoidangtinbatdongsan);
    }

    private void initControl() {
        LNT_danhgiaduan_menu = findViewById(R.id.LNT_danhgiaduan_menu);
        LNT_dangtinbatdongsan_menu = findViewById(R.id.LNT_dangtinbatdongsan_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        LNL_user = findViewById(R.id.LNT_account_menu);
        LNT_history = findViewById(R.id.LNT_history_menu);
        TBR_nguoidangtinbatdongsan = findViewById(R.id.TBR_dangtinbatdongsan_menu);
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
            case R.id.action_logout:
                presenterMenu.FetchLogout();
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
        switch (v.getId()) {
            case R.id.LNT_account_menu:
                intentView(InfoaccountActivity.class);
                break;
            case R.id.LNT_history_menu:
//                presenterMenu.initButtonIntent(1);
                break;
            case R.id.LNT_dangtinbatdongsan_menu:
                presenterMenu.initButtonIntent(2);
                break;
            case R.id.LNT_danhgiaduan_menu:
                presenterMenu.initButtonIntent(3);
                break;
        }
    }

    private void intentView(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
    }

    @Override
    public void onBackPressed() {
        presenterMenu.initButtonIntent(0);
    }

    @Override
    public void IntentView() {

    }

    @Override
    public void onFetchLogout() {

    }

    @Override
    public void onsetVisibility() {

    }
}
