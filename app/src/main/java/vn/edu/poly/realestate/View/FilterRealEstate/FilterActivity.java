package vn.edu.poly.realestate.View.FilterRealEstate;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterFilter.PresenterFilter;
import vn.edu.poly.realestate.Presenter.PresenterFilter.PresenterReponsetoViewFilter;
import vn.edu.poly.realestate.R;

public class FilterActivity extends BaseActivity implements PresenterReponsetoViewFilter, View.OnClickListener {
    PresenterFilter presenterFilter;
    ImageView img_back_details;
    LinearLayout LNL_nharieng, LNL_datnen, LNL_chungcu, LNL_datnenduan;
    FloatingActionButton fab_nharieng, fab_datnen, fab_chungcu, fab_datnenduan;
    Spinner Spinner_distric,spiner_chonhuong;
    TextView txt_phongtam1, txt_phongtam2, txt_phongtam3, txt_phongtam4, txt_phongtam5,
            txt_phongngu1, txt_phongngu2, txt_phongngu3, txt_phongngu4, txt_phongngu5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        LNL_nharieng.setOnClickListener(this);
        LNL_datnen.setOnClickListener(this);
        img_back_details.setOnClickListener(this);
        LNL_chungcu.setOnClickListener(this);
        LNL_datnenduan.setOnClickListener(this);
        txt_phongngu1.setOnClickListener(this);
        txt_phongngu2.setOnClickListener(this);
        txt_phongngu3.setOnClickListener(this);
        txt_phongngu4.setOnClickListener(this);
        txt_phongngu5.setOnClickListener(this);
        txt_phongtam1.setOnClickListener(this);
        txt_phongtam2.setOnClickListener(this);
        txt_phongtam3.setOnClickListener(this);
        txt_phongtam4.setOnClickListener(this);
        txt_phongtam5.setOnClickListener(this);
    }

    private void initData() {
        presenterFilter = new PresenterFilter(this, this);
        presenterFilter.initDataDistrictsDropdown(Spinner_distric);
        presenterFilter.initDataChonhuong(spiner_chonhuong);
        fab_nharieng.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundBlue)));
        fab_nharieng.setImageResource(R.drawable.housewhite);
    }

    private void initControl() {
        img_back_details = findViewById(R.id.img_back_details);
        spiner_chonhuong = findViewById(R.id.Spinner_chonhuong);
        Spinner_distric = findViewById(R.id.Spinner_distric);
        LNL_nharieng = findViewById(R.id.LNL_nharieng);
        LNL_datnen = findViewById(R.id.LNL_datnen);
        LNL_chungcu = findViewById(R.id.LNL_chungcu);
        LNL_datnenduan = findViewById(R.id.LNT_datnenduan);
        fab_nharieng = findViewById(R.id.fab_nharieng);
        fab_datnen = findViewById(R.id.fab_datnen);
        fab_chungcu = findViewById(R.id.fab_chungcu);
        fab_datnenduan = findViewById(R.id.fab_datnenduan);
        txt_phongtam1 = findViewById(R.id.txt_phongtam1);
        txt_phongtam2 = findViewById(R.id.txt_phongtam2);
        txt_phongtam3 = findViewById(R.id.txt_phongtam3);
        txt_phongtam4 = findViewById(R.id.txt_phongtam4);
        txt_phongtam5 = findViewById(R.id.txt_phongtam5);
        txt_phongngu1 = findViewById(R.id.txt_phongngu1);
        txt_phongngu2 = findViewById(R.id.txt_phongngu2);
        txt_phongngu3 = findViewById(R.id.txt_phongngu3);
        txt_phongngu4 = findViewById(R.id.txt_phongngu4);
        txt_phongngu5 = findViewById(R.id.txt_phongngu5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LNL_nharieng:
                presenterFilter.initButtonDataIcon(1);
                break;
            case R.id.LNL_datnen:
                presenterFilter.initButtonDataIcon(2);
                break;
            case R.id.LNL_chungcu:
                presenterFilter.initButtonDataIcon(3);
                break;
            case R.id.LNT_datnenduan:
                presenterFilter.initButtonDataIcon(4);
                break;
            case R.id.txt_phongngu1:
                presenterFilter.initButtonDataIcon(5);
                break;
            case R.id.txt_phongngu2:
                presenterFilter.initButtonDataIcon(6);
                break;
            case R.id.txt_phongngu3:
                presenterFilter.initButtonDataIcon(7);
                break;
            case R.id.txt_phongngu4:
                presenterFilter.initButtonDataIcon(8);
                break;
            case R.id.txt_phongngu5:
                presenterFilter.initButtonDataIcon(9);
                break;
            case R.id.txt_phongtam1:
                presenterFilter.initButtonDataIcon(10);
                break;
            case R.id.txt_phongtam2:
                presenterFilter.initButtonDataIcon(11);
                break;
            case R.id.txt_phongtam3:
                presenterFilter.initButtonDataIcon(12);
                break;
            case R.id.txt_phongtam4:
                presenterFilter.initButtonDataIcon(13);
                break;
            case R.id.txt_phongtam5:
                presenterFilter.initButtonDataIcon(14);
                break;
            case R.id.img_back_details:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onButtonDataIcon(int requestcode) {
        switch (requestcode) {
            case 1:
                fab_nharieng.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundBlue)));
                fab_nharieng.setImageResource(R.drawable.housewhite);
                fab_datnen.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_datnen.setImageResource(R.drawable.architectureblack);
                fab_chungcu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_chungcu.setImageResource(R.drawable.apartmentblack);
                fab_datnenduan.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_datnenduan.setImageResource(R.drawable.designblack);
                break;
            case 2:
                fab_nharieng.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_nharieng.setImageResource(R.drawable.houseblack);
                fab_datnen.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundBlue)));
                fab_datnen.setImageResource(R.drawable.architecturewhite);
                fab_chungcu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_chungcu.setImageResource(R.drawable.apartmentblack);
                fab_datnenduan.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_datnenduan.setImageResource(R.drawable.designblack);
                break;
            case 3:
                fab_nharieng.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_nharieng.setImageResource(R.drawable.houseblack);
                fab_datnen.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_datnen.setImageResource(R.drawable.architectureblack);
                fab_chungcu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundBlue)));
                fab_chungcu.setImageResource(R.drawable.apartmentwhite);
                fab_datnenduan.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_datnenduan.setImageResource(R.drawable.designblack);
                break;
            case 4:
                fab_nharieng.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_nharieng.setImageResource(R.drawable.houseblack);
                fab_datnen.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_datnen.setImageResource(R.drawable.architectureblack);
                fab_chungcu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundWhite)));
                fab_chungcu.setImageResource(R.drawable.apartmentblack);
                fab_datnenduan.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBackgroundBlue)));
                fab_datnenduan.setImageResource(R.drawable.designwhite);
                break;
            case 5:
                txt_phongngu1.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongngu1.setBackground(getResources().getDrawable(R.drawable.textphongngu1));
                txt_phongngu2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu2.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu3.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu4.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 6:
                txt_phongngu1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu1.setBackgroundColor(Color.TRANSPARENT);
                txt_phongngu2.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongngu2.setBackgroundColor(getResources().getColor(R.color.colorBackgroundBlue));
                txt_phongngu3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu3.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu4.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 7:
                txt_phongngu1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu1.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu2.setBackgroundColor(Color.TRANSPARENT);
                txt_phongngu3.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongngu3.setBackgroundColor(getResources().getColor(R.color.colorBackgroundBlue));
                txt_phongngu4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu4.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 8:
                txt_phongngu1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu1.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu2.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu3.setBackgroundColor(Color.TRANSPARENT);
                txt_phongngu4.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongngu4.setBackgroundColor(getResources().getColor(R.color.colorBackgroundBlue));
                txt_phongngu5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 9:
                txt_phongngu1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu1.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu2.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu3.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongngu4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongngu4.setBackgroundColor(Color.TRANSPARENT);
                txt_phongngu5.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongngu5.setBackground(getResources().getDrawable(R.drawable.textphongngu5));
                break;
            case 10:
                txt_phongtam1.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongtam1.setBackground(getResources().getDrawable(R.drawable.textphongngu1));
                txt_phongtam2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam2.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam3.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam4.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 11:
                txt_phongtam1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam1.setBackgroundColor(Color.TRANSPARENT);
                txt_phongtam2.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongtam2.setBackgroundColor(getResources().getColor(R.color.colorBackgroundBlue));
                txt_phongtam3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam3.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam4.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 12:
                txt_phongtam1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam1.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam2.setBackgroundColor(Color.TRANSPARENT);
                txt_phongtam3.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongtam3.setBackgroundColor(getResources().getColor(R.color.colorBackgroundBlue));
                txt_phongtam4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam4.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 13:
                txt_phongtam1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam1.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam2.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam3.setBackgroundColor(Color.TRANSPARENT);
                txt_phongtam4.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongtam4.setBackgroundColor(getResources().getColor(R.color.colorBackgroundBlue));
                txt_phongtam5.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam5.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 14:
                txt_phongtam1.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam1.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam2.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam2.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam3.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam3.setBackground(getResources().getDrawable(R.drawable.textlines2));
                txt_phongtam4.setTextColor(getResources().getColor(R.color.colorTextBlack));
                txt_phongtam4.setBackgroundColor(Color.TRANSPARENT);
                txt_phongtam5.setTextColor(getResources().getColor(R.color.colorTextWhite));
                txt_phongtam5.setBackground(getResources().getDrawable(R.drawable.textphongngu5));
                break;
        }
    }

    @Override
    public void onDataDistrictsDropdown() {

    }

    @Override
    public void onDataChonhuong() {

    }
}
