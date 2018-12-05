package vn.edu.poly.realestate.Component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import vn.edu.poly.realestate.Networking.NetworkStateMonitor;

public class BaseActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    public Snackbar snackbar;
    public static String HTTP = "http://demo.vnlead.webstarterz.com";
    public static String token = "";
    public static String TAG = "BASE_ACTIVITY";
    private final int MY_PERMISSIONS_REQUEST_INTERNET = 10;
    public static SharedPreferences dataLoginScreen;
    public static SharedPreferences.Editor editorScreen;
    public static SharedPreferences dataLoginInfo;
    public static SharedPreferences.Editor editorInfo;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEditor();
        view = getWindow().getDecorView().getRootView();
        InterNet();
    }

    private void initEditor() {
        dataLoginScreen = getSharedPreferences("data_Screen", MODE_PRIVATE);
        dataLoginInfo = getSharedPreferences("data_Info", MODE_PRIVATE);
    }

    private void InterNet() {
        snackbar = Snackbar.make(view, "Not connect internet", Snackbar.LENGTH_INDEFINITE)
                .setAction("Setting", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }
                })
                .setActionTextColor(Color.RED);
        View viewSnackBar = snackbar.getView();
        TextView txt_snack = viewSnackBar.findViewById(android.support.design.R.id.snackbar_text);
        TextView txt_snack_action = viewSnackBar.findViewById(android.support.design.R.id.snackbar_action);
        txt_snack.setTypeface(Typeface.createFromAsset(getAssets(),
                "roboto_regular.ttf"));
        txt_snack_action.setTypeface(Typeface.createFromAsset(getAssets(),
                "roboto_regular.ttf"));
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (new NetworkStateMonitor().checkInterNet(context)){
                    snackbar.dismiss();
                } else {
                    snackbar.show();
                }
            }
        };
    }


}
