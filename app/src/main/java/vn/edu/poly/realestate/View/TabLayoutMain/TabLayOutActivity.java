package vn.edu.poly.realestate.View.TabLayoutMain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterTab.PresenterReponsetoViewTab;
import vn.edu.poly.realestate.Presenter.PresenterTab.PresenterTab;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.DangTinBatDongSan.DangTinBatDongSanActivity;
import vn.edu.poly.realestate.View.FilterRealEstate.FilterActivity;

public class TabLayOutActivity extends BaseActivity implements PresenterReponsetoViewTab, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    PresenterTab presenterTab;
    CircleImageView fab;
    private static final String SELECTED_ITEM = "arg_selected_item";
    private int mSelectedItem;
    BottomNavigationView mBottomNav;
    BottomNavigationMenuView menuView;
    MenuItem selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_lay_out);
        initControl();
        initData();
        initOnClick();

    }

    private void initOnClick() {
        fab.setOnClickListener(this);
        mBottomNav.setOnNavigationItemSelectedListener(this);
    }


    @SuppressLint("RestrictedApi")
    private void initData() {
        presenterTab = new PresenterTab(this, this);
        presenterTab.initFragment(0);
        MenuItem homeItem = mBottomNav.getMenu().getItem(1);
        if (mSelectedItem != homeItem.getItemId()) {
            // Select home item
            mBottomNav.setSelectedItemId(homeItem.getItemId());
        } else {
            super.onBackPressed();
        }
        mBottomNav.getMenu().findItem(R.id.navigation_action_timbatdongsan).setEnabled(false);
    }



    private void initControl() {
        fab = findViewById(R.id.fab);
        mBottomNav = findViewById(R.id.bottom_navigation_view);
        menuView = (BottomNavigationMenuView) mBottomNav.getChildAt(0);
    }

    public void transactionFrangment(Fragment f, String s) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                f, s).commit();
    }


    @Override
    public void onFetchFragment(Fragment fragment, String str) {
        transactionFrangment(fragment, str);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(TabLayOutActivity.this, DangTinBatDongSanActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_action_timbatdongsan:
                mBottomNav.getMenu().findItem(R.id.navigation_action_tindang).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_timbatdongsan).setEnabled(false);
                mBottomNav.getMenu().findItem(R.id.navigation_action_notification).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_account).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_timbatdongsan).setCheckable(true);
                presenterTab.initFragment(0);
                return true;
            case R.id.navigation_action_tindang:
                mBottomNav.getMenu().findItem(R.id.navigation_action_tindang).setEnabled(false);
                mBottomNav.getMenu().findItem(R.id.navigation_action_timbatdongsan).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_notification).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_account).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_tindang).setCheckable(true);
                presenterTab.initFragment(1);
                return true;
            case R.id.navigation_action_notification:
                mBottomNav.getMenu().findItem(R.id.navigation_action_tindang).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_timbatdongsan).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_notification).setEnabled(false);
                mBottomNav.getMenu().findItem(R.id.navigation_action_account).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_notification).setCheckable(true);
                presenterTab.initFragment(2);
                return true;
            case R.id.navigation_action_account:
                mBottomNav.getMenu().findItem(R.id.navigation_action_tindang).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_timbatdongsan).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_notification).setEnabled(true);
                mBottomNav.getMenu().findItem(R.id.navigation_action_account).setEnabled(false);
                mBottomNav.getMenu().findItem(R.id.navigation_action_account).setCheckable(true);
                presenterTab.initFragment(3);
                return true;
        }
        return false;

    }
}
