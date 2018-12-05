package vn.edu.poly.realestate.View.User;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import vn.edu.poly.realestate.R;

public class WalletActivity extends AppCompatActivity {

//    withdrawal: rút tiền

    CollapsingToolbarLayout mCollapsingToolbarLayoutWallet;
    AppBarLayout mAppBarLayoutWallet;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        mToolbar = findViewById(R.id.toolbar_wallet);
        mCollapsingToolbarLayoutWallet = findViewById(R.id.collapsingToolbar_wallet);
        mAppBarLayoutWallet = findViewById(R.id.appBar_wallet);
        mCollapsingToolbarLayoutWallet.setTitleEnabled(false);

        mAppBarLayoutWallet.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isVisible = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                    mToolbar.setTitle(" ");
                }
                if (scrollRange + verticalOffset == 0) {
                    isVisible = true;
                    mCollapsingToolbarLayoutWallet.setTitleEnabled(true);
                    mToolbar.setTitle("dasdasd");
                } else if(isVisible) {
                    isVisible = false;
                }
            }
        });
    }
}
