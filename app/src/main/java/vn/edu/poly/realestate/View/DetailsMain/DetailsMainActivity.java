package vn.edu.poly.realestate.View.DetailsMain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.realestate.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.HotDealObject;
import vn.edu.poly.realestate.Presenter.PresenterDetails.PresenterDetails;
import vn.edu.poly.realestate.Presenter.PresenterDetails.PresenterReponsetoViewDetails;
import vn.edu.poly.realestate.Presenter.PresenterUser.SignIn.PresenterSignIn;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;

public class DetailsMainActivity extends BaseActivity implements View.OnClickListener, PresenterReponsetoViewDetails {
    SeekBar seekBar_value_Details;
    EditText edt_value_money_Details;
    String text;
    ImageView img_back_details, img_question_detailsActivity;
    Button btn_Submid_details;
    int NumberOne = 0;
    TextView txt_tiendautu, txt_address_details, txt_dangduoccoc, txt_tiencoc, txt_tienmua, txt_tiendaututoida, txt_sokhachhangdadautuvao;
    int Sell;
    long Deposit, DepositBuy;
    String DepositSubString, DepositBuySubString;
    private PresenterDetails presenterDetails;
    long phantramdautuLong;
    DecimalFormat decimalFormat, decimalFormatphantram;
    private ViewPager mViewPage;
    private CustomViewPagerAdapter mAdapter;
    private Handler handler;
    private final int delay = 2500;
    LinearLayout LNTlayoutDetails;
    private int pageQuang = 0;
    Runnable runnable = new Runnable() {
        public void run() {
            if (mAdapter.getCount() == pageQuang) {
                pageQuang = 0;
            } else {
                pageQuang++;
            }
            mViewPage.setCurrentItem(pageQuang, true);
            handler.postDelayed(this, delay);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils_main);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        img_question_detailsActivity.setOnClickListener(this);
        btn_Submid_details.setOnClickListener(this);
        img_back_details.setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    private void initData() {
        edt_value_money_Details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edt_value_money_Details.setSelection(String.valueOf(s).length());
                decimalFormat = new DecimalFormat("###,###,###");

                text = s.toString();
                if (text.equals("")) {
                    seekBar_value_Details.setProgress(0);
                    txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + 0 + " (" + 0 + "%)");
                    btn_Submid_details.setEnabled(false);
                    btn_Submid_details.setAlpha((float) 0.5);
                } else {
                    int progressInt = (int) Math.round(Double.parseDouble(s.toString()));
                    if (progressInt > 0 && progressInt <= seekBar_value_Details.getMax()) {
                        seekBar_value_Details.setProgress(progressInt);
                        double phantramdautudouble = Double.valueOf((((double) phantramdautuLong) * Double.parseDouble(s.toString()) / 100));
                        String phantramdautuStr = decimalFormat.format(phantramdautudouble).replace(".", ",");
                        txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + phantramdautuStr + " (" + text + "%)");
                        btn_Submid_details.setEnabled(true);
                        btn_Submid_details.setAlpha(1);
                    }
                    if (progressInt > seekBar_value_Details.getMax()) {
                        edt_value_money_Details.setText(String.valueOf(seekBar_value_Details.getMax()));
                        seekBar_value_Details.setProgress(seekBar_value_Details.getMax());
                        txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + phantramdautuLong + " (" + seekBar_value_Details.getMax() + "%)");
                        btn_Submid_details.setEnabled(true);
                        btn_Submid_details.setAlpha(1);
                    }
                    if (progressInt == 0) {
                        seekBar_value_Details.setProgress(0);
                        txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + 0 + " (" + 0 + "%)");
                        btn_Submid_details.setEnabled(false);
                        btn_Submid_details.setAlpha((float) 0.5);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        seekBar_value_Details.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true) {
                    edt_value_money_Details.setText(String.valueOf(progress));
                } else {
                    edt_value_money_Details.setText(text);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        presenterDetails = new PresenterDetails(this, this);
//        presenterDetails.ReceivedHanleData(Strdangduoccoc, Strtiencoc, Strtienmua);
        presenterDetails.initFetchData();
        txt_sokhachhangdadautuvao.setText("5");
        handler = new Handler();
    }


    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);

    }


    private void initControl() {
        LNTlayoutDetails = findViewById(R.id.LNTlayoutDetails);
        txt_address_details = findViewById(R.id.txt_address_details);
        mViewPage = (ViewPager) findViewById(R.id.viewPager_details);
        img_question_detailsActivity = findViewById(R.id.img_question_detailsActivity);
        txt_sokhachhangdadautuvao = findViewById(R.id.txt_songuoidautu);
        img_back_details = findViewById(R.id.img_back_details);
        seekBar_value_Details = findViewById(R.id.seekBar_value_Details);
        edt_value_money_Details = findViewById(R.id.edt_value_money_Details);
        txt_tiendautu = findViewById(R.id.txt_tiendautu);
        txt_dangduoccoc = findViewById(R.id.txt_dangduoccoc);
        txt_tiencoc = findViewById(R.id.txt_tiencoc);
        txt_tienmua = findViewById(R.id.txt_tienmua);
        txt_tiendaututoida = findViewById(R.id.txt_tiendaututoida);
        btn_Submid_details = findViewById(R.id.btn_Submid_details);
    }

    @Override
    public void onBackPressed() {
        presenterDetails.onBack(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_details:
                onBackPressed();
                break;
            case R.id.btn_Submid_details:
                presenterDetails.onBack(1);
                break;
            case R.id.img_question_detailsActivity:
                presenterDetails.ShowDialogHelp();
                break;
        }
    }

    @Override
    public void onDetailsFetchDataSuccess(String text, int requestCodeTextview) {
        if (requestCodeTextview == 0) {
            txt_dangduoccoc.setText(text);
        }
        if (requestCodeTextview == 1) {
            txt_tiencoc.setText(text);
        }
        if (requestCodeTextview == 2) {
            txt_tienmua.setText(text);
        }
        if (requestCodeTextview == 3) {
            txt_tiendaututoida.setText(text);
        }
        if (requestCodeTextview == 4) {
            edt_value_money_Details.setText(String.valueOf(0));
            phantramdautuLong = Long.parseLong(text);
            seekBar_value_Details.setMax(100);
            txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + 0 + " (" + 0 + "%)");

        }
        if (requestCodeTextview == 6) {
            txt_address_details.setText(text);
        }
    }

    @Override
    public void onBack() {

    }

    @Override
    public void onShowDialogHelp() {

    }

    @Override
    public void onFetchDataId(String images, int requestcode) {
        if (requestcode == 0) {
            Toast.makeText(this, images, Toast.LENGTH_SHORT).show();
        } else if (requestcode == 1) {
            List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
            JSONArray cast = null;
            try {
                cast = new JSONArray(images);
                for (int i = 0; i < cast.length(); i++) {
                    String street = cast.getString(i);
                    mTestData.add(new HotDealObject(street));
                }
                mAdapter = new CustomViewPagerAdapter(this, mTestData);
                mViewPage.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransitionoad1000);
            LNTlayoutDetails.startAnimation(animation);
            LNTlayoutDetails.setVisibility(View.GONE);
        }

    }

}
