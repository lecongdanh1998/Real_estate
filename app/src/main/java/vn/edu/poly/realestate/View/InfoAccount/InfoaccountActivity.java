package vn.edu.poly.realestate.View.InfoAccount;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.AdapterTabLayoutFrangment;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.InfoAccount.historycoins.historycoins;
import vn.edu.poly.realestate.View.InfoAccount.historycustomer.historycustomer;
import vn.edu.poly.realestate.View.InfoAccount.infocustomer.infocustomer;

public class InfoaccountActivity extends Fragment {
    View view;
    public TabLayout tab_layout;
    public ViewPager mViewPager;
    ArrayList listFragment;
    ArrayList<String> listTitle;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    public TextView tabOne, tabTwo, tabThree;
    public int index_change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_infoaccount, container, false);
        initControl();
        initData();
        initOnClick();
        createTabIcons();
        return view;

    }

    private void initControl() {
        tab_layout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tab_layout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        listFragment = new ArrayList();
        listFragment.add(new infocustomer());
        listFragment.add(new historycoins());
        listFragment.add(new historycustomer());
        listTitle = new ArrayList<>();

        listTitle.add(getResources().getString(R.string.txt_info));
        listTitle.add(getResources().getString(R.string.txt_historycoins));
        listTitle.add(getResources().getString(R.string.txt_historydeposit));
        adapterTabLayoutFrangment = new AdapterTabLayoutFrangment(
                getChildFragmentManager(), getContext(), listTitle, listFragment
        );

        mViewPager.setAdapter(adapterTabLayoutFrangment);
    }

    private void initOnClick() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabOne.setText(getResources().getString(R.string.txt_info));
                        tabOne.setTextColor(getResources().getColor(R.color.colorTextBlue));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_historycoins));
                        tabTwo.setTextColor(getResources().getColor(R.color.colorTextBlack));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        tabThree.setText(getResources().getString(R.string.txt_historydeposit));
                        tabThree.setTextColor(getResources().getColor(R.color.colorTextBlack));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        break;
                    case 1:
                        tabOne.setText(getResources().getString(R.string.txt_info));
                        tabOne.setTextColor(getResources().getColor(R.color.colorTextBlack));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_historycoins));
                        tabTwo.setTextColor(getResources().getColor(R.color.colorTextBlue));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_historydeposit));
                        tabThree.setTextColor(getResources().getColor(R.color.colorTextBlack));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        break;
                    case 2:
                        tabOne.setText(getResources().getString(R.string.txt_info));
                        tabOne.setTextColor(getResources().getColor(R.color.colorTextBlack));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_historycoins));
                        tabTwo.setTextColor(getResources().getColor(R.color.colorTextBlack));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_historydeposit));
                        tabThree.setTextColor(getResources().getColor(R.color.colorTextBlue));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon giỏ hàng
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createTabIcons() {
        tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabsarena, null);
        tabOne.setText(getContext().getResources().getString(R.string.txt_info));
        tabOne.setTextColor(getContext().getResources().getColor(R.color.colorTextBlue));
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabsarena, null);
        tabTwo.setText(getContext().getResources().getString(R.string.txt_historycoins));
        tabTwo.setTextColor(getContext().getResources().getColor(R.color.colorTextBlack));
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan
        tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabsarena, null);
        tabThree.setText(getContext().getResources().getString(R.string.txt_historydeposit));
        tabThree.setTextColor(getContext().getResources().getColor(R.color.colorTextBlack));
        tab_layout.getTabAt(2).setCustomView(tabThree);
        mViewPager.setCurrentItem(index_change);
    }
}
