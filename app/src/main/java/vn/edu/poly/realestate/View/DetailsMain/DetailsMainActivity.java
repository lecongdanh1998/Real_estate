package vn.edu.poly.realestate.View.DetailsMain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterDetails.PresenterDetails;
import vn.edu.poly.realestate.Presenter.PresenterDetails.PresenterReponsetoViewDetails;
import vn.edu.poly.realestate.Presenter.PresenterUser.SignIn.PresenterSignIn;
import vn.edu.poly.realestate.R;

public class DetailsMainActivity extends BaseActivity implements View.OnClickListener,PresenterReponsetoViewDetails {
    SeekBar seekBar_value_Details;
    EditText edt_value_money_Details;
    String text;
    ImageView img_back_details;
    int NumberOne = 0;
    TextView txt_tiendautu, txt_dangduoccoc, txt_tiencoc, txt_tienmua,txt_tiendaututoida;
    String Strtiendautu,Strdangduoccoc,Strtiencoc,Strtienmua;
    int Sell;
    long Deposit, DepositBuy;
    String DepositSubString, DepositBuySubString;
    private PresenterDetails presenterDetails;
    long phantramdautuLong;
    DecimalFormat decimalFormat,decimalFormatphantram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils_main);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
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
                    txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + 0 + " Xu" + " (" + 0 + "%)");
                } else {
                    int progressInt = (int) Math.round(Double.parseDouble(s.toString()));
                    if (progressInt > 0 && progressInt <= seekBar_value_Details.getMax()) {
                        seekBar_value_Details.setProgress(progressInt);
                        double phantramdautudouble = Double.valueOf((((double) phantramdautuLong)*Double.parseDouble(s.toString())/100));
                        String phantramdautuStr = decimalFormat.format(phantramdautudouble).replace(".", ",");
                        txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + phantramdautuStr + " Xu" + " (" + text + "%)");

                    }
                    if (progressInt > seekBar_value_Details.getMax()) {
                        edt_value_money_Details.setText(String.valueOf(seekBar_value_Details.getMax()));
                        seekBar_value_Details.setProgress(seekBar_value_Details.getMax());
                        txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + phantramdautuLong + " Xu" + " (" + seekBar_value_Details.getMax() + "%)");

                    }
                    if(progressInt == 0){
                        seekBar_value_Details.setProgress(0);
                        txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + 0 + " Xu" + " (" + 0 + "%)");

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


        Strdangduoccoc = dataLoginInfo.getString("Deposit","");
        Strtiencoc = dataLoginInfo.getString("DepositBuy","");
        Strtienmua = dataLoginInfo.getString("Sell","");

        presenterDetails = new PresenterDetails(this,this);
        presenterDetails.ReceivedHanleData(Strdangduoccoc,Strtiencoc,Strtienmua);
        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initControl() {
        img_back_details = findViewById(R.id.img_back_details);
        seekBar_value_Details = findViewById(R.id.seekBar_value_Details);
        edt_value_money_Details = findViewById(R.id.edt_value_money_Details);
        txt_tiendautu = findViewById(R.id.txt_tiendautu);
        txt_dangduoccoc = findViewById(R.id.txt_dangduoccoc);
        txt_tiencoc = findViewById(R.id.txt_tiencoc);
        txt_tienmua = findViewById(R.id.txt_tienmua);
        txt_tiendaututoida = findViewById(R.id.txt_tiendaututoida);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_details:
                overridePendingTransition(R.anim.stay_still, R.anim.exit_on_right);
                onBackPressed();
                break;
        }
    }

    @Override
    public void onDetailsFetchDataSuccess(String text, int requestCodeTextview) {
        if(requestCodeTextview == 0){
            txt_dangduoccoc.setText(text);
        }
        if(requestCodeTextview == 1){
            txt_tiencoc.setText(text);
        }
        if(requestCodeTextview == 2){
            txt_tienmua.setText(text);
        }
        if(requestCodeTextview == 3){
            txt_tiendaututoida.setText(text);
        }
        if(requestCodeTextview == 4){
            edt_value_money_Details.setText(String.valueOf(0));
            phantramdautuLong = Long.parseLong(text);
            seekBar_value_Details.setMax(100);
            txt_tiendautu.setText(getResources().getString(R.string.txt_tiendautucuaban) + " : " + 0 + " Xu" + " (" + 0 + "%)");

        }
    }
}
