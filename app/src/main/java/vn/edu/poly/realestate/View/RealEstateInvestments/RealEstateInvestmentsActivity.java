package vn.edu.poly.realestate.View.RealEstateInvestments;

import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.realestate.Adapter.RealEstimateInvestmentAdapterTabLayout;
import vn.edu.poly.realestate.R;

public class RealEstateInvestmentsActivity extends AppCompatActivity implements View
        .OnClickListener, TabLayout.BaseOnTabSelectedListener {

    private TabLayout tabLayout_real_estimate_investment;
    private TabItem tabItem_investing_upland_real_estimate, tabItem_invested_upland_real_estimate;
    private Toolbar toobar_details;
    private ImageView img_back_details;
    private TextView txt_title_details;
    private ViewPager viewPager_real_estimate_investment;
    private RealEstimateInvestmentAdapterTabLayout adapterTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_estate_investments);

        initView();
        initEventButton();
        initData();
        initListView();
    }

    private void initView() {
        tabLayout_real_estimate_investment = findViewById(R.id.tabLayout_real_estimate_investment);
        tabItem_investing_upland_real_estimate = findViewById(R.id
                .tabItem_investing_upland_real_estimate);
        tabItem_invested_upland_real_estimate = findViewById(R.id
                .tabItem_invested_upland_real_estimate);
        toobar_details = findViewById(R.id.toobar_details);
        setSupportActionBar(toobar_details);
        img_back_details = findViewById(R.id.img_back_details);
        txt_title_details = findViewById(R.id.txt_title_details);
        txt_title_details.setText("Danh sách bất động sản đầu tư");
        viewPager_real_estimate_investment = findViewById(R.id.viewPager_real_estimate_investment);
    }

    private void initEventButton() {
        img_back_details.setOnClickListener(this);
        tabLayout_real_estimate_investment.addOnTabSelectedListener(this);
    }

    private void initData() {
        adapterTabLayout = new RealEstimateInvestmentAdapterTabLayout(getSupportFragmentManager()
                , tabLayout_real_estimate_investment.getTabCount());
        viewPager_real_estimate_investment.setAdapter(adapterTabLayout);
        viewPager_real_estimate_investment.addOnPageChangeListener(new TabLayout
                .TabLayoutOnPageChangeListener(tabLayout_real_estimate_investment));
    }

    private void initListView() {
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager_real_estimate_investment.setCurrentItem(tab.getPosition());
        switch (tab.getPosition()) {
            case 1:
//                setBackground(R.color.colorPrimaryButton);
                break;
            case 2:
//                setBackground(R.color.colorDeleteButton);
                break;
        }
        viewPager_real_estimate_investment.addOnPageChangeListener(new TabLayout
                .TabLayoutOnPageChangeListener(tabLayout_real_estimate_investment));
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setBackground(int color) {
        tabLayout_real_estimate_investment.setBackgroundColor(ContextCompat.getColor(this, color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, color));
        } else {
            Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
            window.setStatusBarColor(ContextCompat.getColor(this, color));

        }
        viewPager_real_estimate_investment.addOnPageChangeListener(new TabLayout
                .TabLayoutOnPageChangeListener(tabLayout_real_estimate_investment));
    }
}
