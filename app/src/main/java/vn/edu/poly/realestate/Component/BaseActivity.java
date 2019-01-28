package vn.edu.poly.realestate.Component;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

import vn.edu.poly.realestate.Networking.NetworkStateMonitor;
import vn.edu.poly.realestate.View.MainActivity;

public class BaseActivity extends AppCompatActivity implements GoogleApiClient
        .ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    BroadcastReceiver broadcastReceiver;
    public Snackbar snackbar;
    public static String HTTP = "http://demo.vnlead.webstarterz.com";
    public static String token = "";
    public static String TAG = "BASE_ACTIVITY";
    private Location location;
    private Location currentLocation;
    private GoogleApiClient googleApiClient;
    private static final int PLAY_SERVICE_REQUEST_CODE = 123;
    private LocationRequest locationRequest;
    private ArrayList<String> permissionToRequest;
    private ArrayList<String> permissionRejected;
    private ArrayList<String> permissions;
    private static final int UPDATE_INTERVAL = 5000, FAST_UPDATE = 3000;
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
    public static SharedPreferences dataAddress;
    public static SharedPreferences.Editor editorAddress;
    private static final int REQUEST_CODE_PERMISSION = 111;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEditor();
        view = getWindow().getDecorView().getRootView();
        InterNet();
        locationPermission();
    }

    private void initEditor() {
        dataLoginUser = getSharedPreferences("data_User", MODE_PRIVATE);
        dataStatus = getSharedPreferences("data_Status", MODE_PRIVATE);
        dataLoginScreen = getSharedPreferences("data_Screen", MODE_PRIVATE);
        dataLoginInfo = getSharedPreferences("data_Info", MODE_PRIVATE);
        dataInvestUpland = getSharedPreferences("data_Invest", MODE_PRIVATE);
        dataInternet = getSharedPreferences("data_Internet", MODE_PRIVATE);
        dataAddressCity = getSharedPreferences("data_AddressCity", MODE_PRIVATE);
        dataAddress = getSharedPreferences("data_Address", MODE_PRIVATE);
    }

    private void locationPermission() {
        permissionRejected = new ArrayList<>();
        permissionToRequest = new ArrayList<>();
        permissions = new ArrayList<>();

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        permissionToRequest = permissionReject(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionToRequest.size() > 0) {
                requestPermissions(permissionToRequest.toArray(new String[permissionToRequest
                        .size()]), REQUEST_CODE_PERMISSION);
            }
        }

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
    }

    private ArrayList<String> permissionReject(ArrayList<String> permissions) {
        ArrayList<String> listResult = new ArrayList<>();
        for (String per : permissions) {
            if (!hashPermission(per)) {
                listResult.add(per);
            }
        }
        return listResult;
    }

    private boolean hashPermission(String per) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(per) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
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
                    editorInternet.putInt("status", 1);
                    editorInternet.commit();
                    snackbar.dismiss();
                } else {
                    editorInternet.putInt("status", 0);
                    editorInternet.commit();
                    snackbar.show();
                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
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
        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
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
    protected void onResume() {
        super.onResume();
        if (!checkPlayService()) {
            Toast.makeText(this, "Device not support!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPlayService() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICE_REQUEST_CODE);
            } else {

            }
            return false;
        }
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (currentLocation != null) {
        }

        getLocationUpdate();
    }

    private void getLocationUpdate() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Enable permission location", Toast.LENGTH_SHORT).show();
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(BaseActivity.this,
                "onConnectionSuspended: " + String.valueOf(i),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(BaseActivity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                for (String perm : permissionToRequest) {
                    if (!hashPermission(perm)) {
                        permissionRejected.add(perm);
                    }
                }
                if (permissionRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionRejected.get(0))) {
                            new AlertDialog.Builder(getApplicationContext())
                                    .setMessage("Enable location permission")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                                                requestPermissions(permissionRejected.toArray(new
                                                                String[permissionRejected.size()]),
                                                        REQUEST_CODE_PERMISSION);
                                            }
                                        }
                                    })
                                    .setNegativeButton("Cancel", null)
                                    .create()
                                    .show();
                        }
                    }
                } else {
                    if (googleApiClient != null) {
                        googleApiClient.connect();
                    }
                }
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
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng mLocationCurrent = new LatLng(106.67806, 10.83232);
        LatLng mLocationCompany = new LatLng(106.68096, 10.82970);
        double mDistance = SphericalUtil.computeDistanceBetween(mLocationCurrent, mLocationCompany);
        Log.d(TAG, "onLocationChanged: " + mDistance);
    }

}
