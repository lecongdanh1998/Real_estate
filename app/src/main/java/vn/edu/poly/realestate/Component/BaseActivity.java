package vn.edu.poly.realestate.Component;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    public static SharedPreferences dataLoginUser;
    public static SharedPreferences.Editor editorUser;
    public static SharedPreferences dataInvestUpland;
    public static SharedPreferences.Editor editorInvestUpland;
    public static SharedPreferences dataStatus;
    public static SharedPreferences.Editor editorStatus;
    public static SharedPreferences dataInternet;
    public static SharedPreferences.Editor editorInternet;
    public static SharedPreferences dataAddressCity;
    public static SharedPreferences.Editor editorAddressCity;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEditor();
        view = getWindow().getDecorView().getRootView();
        InterNet();
    }

    private void initEditor() {
        dataLoginUser = getSharedPreferences("data_User", MODE_PRIVATE);
        dataStatus = getSharedPreferences("data_Status", MODE_PRIVATE);
        dataLoginScreen = getSharedPreferences("data_Screen", MODE_PRIVATE);
        dataLoginInfo = getSharedPreferences("data_Info", MODE_PRIVATE);
        dataInvestUpland = getSharedPreferences("data_Invest", MODE_PRIVATE);
        dataInternet = getSharedPreferences("data_Internet", MODE_PRIVATE);
        dataAddressCity = getSharedPreferences("data_AddressCity", MODE_PRIVATE);
    }

    private void InterNet() {
        snackbar = Snackbar.make(view, "Not connect internet", Snackbar.LENGTH_INDEFINITE)
                .setAction("Setting", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
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
                editorInternet = dataInternet.edit();
                if (new NetworkStateMonitor().checkInterNet(context)) {
                    editorInternet.putInt("status",1);
                    editorInternet.commit();
                    snackbar.dismiss();
                } else {
                    editorInternet.putInt("status",0);
                    editorInternet.commit();
                    snackbar.show();
                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void checkInternetPermission(Activity thisActivity) {

        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Manifest.permission.INTERNET)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                ActivityCompat.requestPermissions(thisActivity,
                        new String[]{Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);
            }
        } else {
            // Permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_INTERNET: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    finish();
                    System.exit(0);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
