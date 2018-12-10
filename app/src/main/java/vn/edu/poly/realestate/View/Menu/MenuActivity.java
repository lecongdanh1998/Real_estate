package vn.edu.poly.realestate.View.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterMenu.PresenterMenu;
import vn.edu.poly.realestate.Presenter.PresenterMenu.PresenterReponsetoViewMenu;
import vn.edu.poly.realestate.R;

public class MenuActivity extends BaseActivity implements View.OnClickListener,PresenterReponsetoViewMenu {
    LinearLayout LNL_user,LNT_history;
    PresenterMenu presenterMenu;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        LNL_user.setOnClickListener(this);
        LNT_history.setOnClickListener(this);
    }

    private void initData() {
        setSupportActionBar(toolbar);
        presenterMenu = new PresenterMenu(this,this);
    }

    private void initControl() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        LNL_user = findViewById(R.id.LNT_account_menu);
        LNT_history = findViewById(R.id.LNT_history_menu);

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
        switch (v.getId()){
            case R.id.LNT_account_menu:
                break;
            case R.id.LNT_history_menu:
                presenterMenu.initButtonIntent(1);
                break;
        }
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
}
