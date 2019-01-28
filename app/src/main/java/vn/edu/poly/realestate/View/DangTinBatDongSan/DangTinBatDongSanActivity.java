package vn.edu.poly.realestate.View.DangTinBatDongSan;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vn.edu.poly.realestate.Adapter.AdapterFilterChonhuong;
import vn.edu.poly.realestate.Adapter.AdapterFilterGiayChuQuyen;
import vn.edu.poly.realestate.Adapter.AdapterFilterLoaiDat;
import vn.edu.poly.realestate.Adapter.AdapterFilterPhong;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterDangTinBatDongSan.PresenterDangTinBatDongSan;
import vn.edu.poly.realestate.Presenter.PresenterDangTinBatDongSan.PresenterReponsetoViewDangTinBatDongSan;
import vn.edu.poly.realestate.R;

public class DangTinBatDongSanActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnTouchListener, GoogleMap.OnMapLongClickListener, GoogleMap
                .OnCameraMoveListener, GoogleMap.OnCameraMoveCanceledListener, GoogleMap
                .OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener, View.OnClickListener, OnMapReadyCallback, PresenterReponsetoViewDangTinBatDongSan {
    private double latitude, longitude, myLat, myLng;
    private GoogleMap mMap;
    RelativeLayout relativeLayout;
    String city, ward, dictris;
    private static final int MY_LOCATION_REQUEST_CODE = 1000;
    private GoogleApiClient googleApiClient;
    private Location currentLocation;
    private LocationRequest locationRequest;
    Spinner spin_Wards, spin_District, spin_city;
    PresenterDangTinBatDongSan presenterPost;
    double getLatitude, getLongitude;
    Button btn_continue, btn_cancel;
    int intent = 0;
    LinearLayout llBottomSheet;
    BottomSheetBehavior bottomSheetBehavior;
    CardView cardView_button_find, cardview_find;
    FloatingActionButton actionButton;
    Spinner spinner_loaibatdongsan, spinner_chonhuong, spinner_phongngu, spinner_phongtam, spinner_giaychuquyen;
    EditText edt_datcoc, edt_giathuongluong, edt_gia, edt_find, edt_tenduong;
    Geocoder geocoder;
    List<Address> addresses;
    ImageView img_market;
    private Marker mMarker;
    LatLng latLng;
    int Internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin_bat_dong_san);
        initControl();
        initData();
        initOnClick();
    }


    private void initControl() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spin_Wards = findViewById(R.id.spinner_ward);
        btn_cancel = findViewById(R.id.btn_cancel);
        img_market = findViewById(R.id.img_market);
        spin_District = findViewById(R.id.spinner_distric);
        spin_city = findViewById(R.id.spinner_city);
        btn_continue = findViewById(R.id.btn_continue);
        cardView_button_find = findViewById(R.id.cardView_button_find);
        cardview_find = findViewById(R.id.cardview_find);
        edt_find = findViewById(R.id.edt_find);
        actionButton = findViewById(R.id.fabfind);
        relativeLayout = findViewById(R.id.relativeLayout);
        spinner_loaibatdongsan = findViewById(R.id.spinner_loaibatdongsan);
        spinner_chonhuong = findViewById(R.id.spinner_chonhuong);
        spinner_phongngu = findViewById(R.id.spinner_phongngu);
        spinner_phongtam = findViewById(R.id.spinner_phongtam);
        spinner_giaychuquyen = findViewById(R.id.spinner_giaychuquyen);
        edt_datcoc = findViewById(R.id.edt_datcoc);
        edt_giathuongluong = findViewById(R.id.edt_giathuongluong);
        edt_gia = findViewById(R.id.edt_gia);
        edt_tenduong = findViewById(R.id.edt_tenduong);

    }

    private void initData() {
        presenterPost = new PresenterDangTinBatDongSan(this, this);
        llBottomSheet = (LinearLayout) findViewById(R.id.chunhavabatdongsan);
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.setHideable(false);
        presenterPost.initDataLoaiDat();
        presenterPost.initDataChonhuong();
        presenterPost.initDataPhong();
        presenterPost.initDataGiayChuQuyen();
        presenterPost.initEditTextPrice(edt_gia, edt_giathuongluong, edt_datcoc);
        presenterPost.initSpinnerAddress(spin_Wards, spin_District, spin_city);
        presenterPost.initOnKeyBoard(cardView_button_find);
        edt_find.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!edt_find.getText().toString().equals("")) {
                        presenterPost.initEditSearch(edt_find.getText().toString());
                    } else {
                        Toast.makeText(DangTinBatDongSanActivity.this, "" + v.toString(), Toast.LENGTH_SHORT).show();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                                .INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edt_find.getApplicationWindowToken(), 0);
                    }
                }
                return false;
            }
        });
    }


    private void initOnClick() {
        btn_cancel.setOnClickListener(this);
        img_market.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
        actionButton.setOnClickListener(this);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("NANNA", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        Log.d("NANNA", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        actionButton.animate().rotation(180).start();
                        Log.d("NANNA", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("NANNA", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("NANNA", "STATE_SETTLING");
                        break;
                    default:
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
            }
        });
        edt_tenduong.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenterPost.initFindDataAddress(edt_tenduong.getText().toString(), city, dictris, ward);
                }
                return false;
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue:
                edt_find.setEnabled(false);
                relativeLayout.setVisibility(View.VISIBLE);
                bottomSheetBehavior.setPeekHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                actionButton.animate().rotation(180).start();
                intent = 1;
                break;
            case R.id.fabfind:
                onBackPressed();
                break;
            case R.id.relativeLayout:
                onBackPressed();
                break;
            case R.id.img_market:
                initAddMapsOnClick(getLatitude, getLongitude);
                break;
            case R.id.btn_cancel:
                onBackPressed();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (intent == 1) {
            edt_find.setEnabled(true);
            relativeLayout.setVisibility(View.INVISIBLE);
            actionButton.animate().rotation(0).start();
            bottomSheetBehavior.setPeekHeight(0);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            intent = 0;
        } else if (intent == 0) {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
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
            getLocationUpdate();
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
    public void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            if (Internet == 0) {
                googleApiClient.connect();
                Internet = 1;
            }
        }
    }

    //
    @Override
    public void onPause() {
        super.onPause();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            myLat = location.getLatitude();
            myLng = location.getLongitude();
            if (mMap != null) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(myLat, myLng)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myLat, myLng)));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10f));
            }
        }

    }

    @Override
    public void onCameraIdle() {

    }

    @Override
    public void onCameraMoveCanceled() {

    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onCameraMoveStarted(int i) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initMapsFind();

    }

    private void initMapsFind() {

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (addresses != null) {
                    addresses.clear();
                }
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .position(cameraPosition.target)
                        .title(cameraPosition.toString())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_3d_rotation_black_24dp)).draggable(true)
                );
                getLatitude = cameraPosition.target.latitude;
                getLongitude = cameraPosition.target.longitude;
                String strAdd = "";
                geocoder = new Geocoder(DangTinBatDongSanActivity.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(getLatitude, getLongitude, 1);
                    if (addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder("");

                        for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                        }
                        strAdd = strReturnedAddress.toString();
                        edt_find.setText(strAdd);
                        Log.d("My Current loction address", strReturnedAddress.toString());
                    } else {
                        Log.d("My Current loction address", "No Address returned!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("My Current loction address", "Canont get Address!");
                }


                Log.i("centerLat", String.valueOf(cameraPosition.target.latitude));

                Log.i("centerLong", String.valueOf(cameraPosition.target.longitude));
            }
        });
    }

    private void initAddMapsOnClick(double latitude, double longitude) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_3d_rotation_black_24dp)).draggable(true));
        Circle circle1 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(1000)
                .strokeColor(Color.TRANSPARENT));
        float currentZoomLevel = getZoomLevel(circle1) + 0.5f;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(circle1.getCenter
                (), currentZoomLevel));
    }

    public float getZoomLevel(Circle circle) {
        int zoomLevel = 11;
        if (circle != null) {
            double radius = circle.getRadius() + circle.getRadius() / 2;
            double scale = radius / 500;
            zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    @Override
    public void onIntentView() {

    }

    @Override
    public void onSpinnerAddress(String requestAddress, int requestcode) {
        if (requestcode == 0) {
            city = requestAddress;
        } else if (requestcode == 1) {
            dictris = requestAddress;
        } else if (requestcode == 2) {
            ward = requestAddress;
        }
    }


    @Override
    public void onDataLoaiDat(AdapterFilterLoaiDat adapterFilterLoaiDat) {
        spinner_loaibatdongsan.setAdapter(adapterFilterLoaiDat);
        adapterFilterLoaiDat.notifyDataSetChanged();
    }

    @Override
    public void onDataPhong(AdapterFilterPhong adapterFilterPhong) {
        spinner_phongngu.setAdapter(adapterFilterPhong);
        spinner_phongtam.setAdapter(adapterFilterPhong);
        adapterFilterPhong.notifyDataSetChanged();
    }

    @Override
    public void onDataGiayChuQuyen(AdapterFilterGiayChuQuyen adapterFilterGiayChuQuyen) {
        spinner_giaychuquyen.setAdapter(adapterFilterGiayChuQuyen);
        adapterFilterGiayChuQuyen.notifyDataSetChanged();
    }

    @Override
    public void onFetchAddress(String str) {
        Search(str);
    }

    @Override
    public void onEditTextSearch(String str) {
        Search(str);
    }

    @Override
    public void onKeyboard() {

    }

    public void Search(String search) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> list = new ArrayList<>();
        MarkerOptions markerOptions = new MarkerOptions();
        try {
            list = geocoder.getFromLocationName(search, 1);
        } catch (IOException e) {
            Log.d("geoLocate1: ", "" + e.getMessage());
        }
        if (list.size() > 0) {
            Address address = list.get(0);
            latLng = new LatLng(address.getLatitude(), address.getLongitude());
            markerOptions.position(latLng);
            if (mMarker != null) {
                mMarker.remove();
            }
            initAddMapsOnClick(address.getLatitude(), address.getLongitude());
//            mMarker = mMap.addMarker(markerOptions
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_3d_rotation_black_24dp)).draggable(true));

            Log.d("geoLocate2: ", "" + address.toString());
        }
    }

    @Override
    public void onDataChonhuong(AdapterFilterChonhuong adapterFilterChonhuong) {
        spinner_chonhuong.setAdapter(adapterFilterChonhuong);
        adapterFilterChonhuong.notifyDataSetChanged();


    }

}
