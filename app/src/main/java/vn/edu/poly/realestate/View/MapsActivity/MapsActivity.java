package vn.edu.poly.realestate.View.MapsActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.data.kml.KmlLayer;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import vn.edu.poly.realestate.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.HotDealObject;
import vn.edu.poly.realestate.Model.ModelMaps.CustomClusterRenderer;
import vn.edu.poly.realestate.Model.ModelMaps.MapDirectionModel;
import vn.edu.poly.realestate.Model.ModelMaps.ModelMaps;
import vn.edu.poly.realestate.Presenter.PresenterMaps.PresenterMaps;
import vn.edu.poly.realestate.Presenter.PresenterMaps.PresenterReponsetoViewMaps;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.DangTinBatDongSan.DangTinBatDongSanActivity;
import vn.edu.poly.realestate.View.MainActivity;

public class MapsActivity extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnTouchListener, GoogleMap.OnMapLongClickListener, GoogleMap
                .OnCameraMoveListener, GoogleMap.OnCameraMoveCanceledListener, GoogleMap
                .OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener, PresenterReponsetoViewMaps, OnClickListener {
    PresenterMaps presenterMaps;
    Button btn_datlichxem;
    private ClusterManager<ModelMaps> mClusterManager;
    private static final int MY_LOCATION_REQUEST_CODE = 1000;
    private GoogleMap mMap;
    private LatLngBounds bounds;
    LinearLayout llBottomSheet, heighline, linearLayout;
    BottomSheetBehavior bottomSheetBehavior;
    CircleOptions circleOptions;
    private double latitude, longitude, myLat, myLng;
    View viewMaps;
    private Location currentLocation;
    private LatLng exact_location, latLng;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    CardView cardview;
    private Marker mMarker;
    private LatLngBounds.Builder builder;
    String addressEditor;
    Circle circle;
    ImageView img_sort;
    float[] distance;
    private int wScreen, hScreen, padding;
    CustomClusterRenderer renderer;
    ArrayList<LatLng> listLocation;
    EditText edt_find;
    Polyline line;
    int Internet, showhide;
    CirclePageIndicator circlePageIndicator;
    ImageView imgtop, fab;
    ViewPager viewPager;
    private int pageQuang = 0;
    CustomViewPagerAdapter mAdapter;
    private Handler handler;
    private final int delay = 2500;
    FloatingActionButton actionButton1, actionButton2, actionButton3, fab_icon1, fab_icon2, fab_icon3;
    Runnable runnable = new Runnable() {
        public void run() {
            if (mAdapter.getCount() == pageQuang) {
                pageQuang = 0;
            } else {
                pageQuang++;
            }
            viewPager.setCurrentItem(pageQuang, true);
            handler.postDelayed(this, delay);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewMaps = inflater.inflate(R.layout.activity_maps, container, false);
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initControl();
        initData();
        initOnClick();
        return viewMaps;

    }


    private void initOnClick() {
        btn_datlichxem.setOnClickListener(this);
        actionButton1.setOnClickListener(this);
        actionButton2.setOnClickListener(this);
        actionButton3.setOnClickListener(this);
        fab_icon1.setOnClickListener(this);
        fab_icon2.setOnClickListener(this);
        fab_icon3.setOnClickListener(this);
        img_sort.setOnClickListener(this);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        imgtop.animate().rotation(0).start();
                        bottomSheetBehavior.setHideable(false);
                        linearLayout.setVisibility(View.VISIBLE);
                        showhide = 0;
                        Log.d("NANNA", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        bottomSheetBehavior.setHideable(true);
                        if (showhide == 0) {
                            linearLayout.setVisibility(View.GONE);
                        }
                        Log.d("NANNA", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        showhide = 1;
                        linearLayout.setVisibility(View.GONE);
                        imgtop.animate().rotation(180).start();
                        Log.d("NANNA", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        linearLayout.setVisibility(View.VISIBLE);
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


    }


    private void initData() {
        presenterMaps = new PresenterMaps(this, getContext());
        edt_find.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!edt_find.getText().toString().equals("")) {
                        presenterMaps.initEditSearch(edt_find.getText().toString());
                    } else {
                        Toast.makeText(getContext(), "" + v.toString(), Toast.LENGTH_SHORT).show();
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context
                                .INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edt_find.getApplicationWindowToken(), 0);
                    }
                }
                return false;
            }
        });
        llBottomSheet = (LinearLayout) getActivity().findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setPeekHeight(0);
        presenterMaps.initFetchData();
        handler = new Handler();
    }


    private void getLocationUpdate() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "Enable permission location", Toast.LENGTH_SHORT).show();
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                locationRequest, this);
    }

    public void Search(String search) {
        Geocoder geocoder = new Geocoder(getContext());
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
            mMarker = mMap.addMarker(markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_36dp)));
            getDirection(new LatLng(myLat, myLng), latLng, "");
            Log.d("geoLocate2: ", "" + address.toString());
        }
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
    public void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            if (Internet == 0) {
                googleApiClient.connect();
                Internet = 1;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
        handler.removeCallbacks(runnable);
    }

    private void initControl() {
        circlePageIndicator = getActivity().findViewById(R.id.indicator);
        fab = getActivity().findViewById(R.id.fab);
        edt_find = viewMaps.findViewById(R.id.edt_find);
        btn_datlichxem = getActivity().findViewById(R.id.btn_datlichxem);
        fab_icon1 = viewMaps.findViewById(R.id.fab_icon1);
        fab_icon2 = viewMaps.findViewById(R.id.fab_icon2);
        fab_icon3 = viewMaps.findViewById(R.id.fab_icon3);
        actionButton1 = viewMaps.findViewById(R.id.fab1);
        actionButton2 = viewMaps.findViewById(R.id.fab2);
        actionButton3 = viewMaps.findViewById(R.id.fab3);
        img_sort = viewMaps.findViewById(R.id.img_sort);
        cardview = viewMaps.findViewById(R.id.cardview);
        imgtop = getActivity().findViewById(R.id.imgtop);
        heighline = getActivity().findViewById(R.id.heighline);
        viewPager = getActivity().findViewById(R.id.pager);
        linearLayout = getActivity().findViewById(R.id.linearLayout);
        addressEditor = BaseActivity.dataAddress.getString("addressEditor", "");
    }

    @SuppressLint("RestrictedApi")
    private void showicon() {
        fab_icon1.show();
        fab_icon2.show();
        fab_icon3.show();
    }

    @SuppressLint("RestrictedApi")
    private void hideicon() {
        fab_icon1.hide();
        fab_icon2.hide();
        fab_icon3.hide();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public ClusterManager.OnClusterItemClickListener<ModelMaps> mClusterItemClickListener = new ClusterManager.OnClusterItemClickListener<ModelMaps>() {

        @Override
        public boolean onClusterItemClick(ModelMaps item) {
            // Some actions here
            heighline.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    heighline.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    bottomSheetBehavior.setPeekHeight(heighline.getHeight());
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });
//            imgtop.animate().rotation(180).start();
            return false;
        }
    };

    private void setUpClusterer() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLat, myLng), 10));
        mClusterManager = new ClusterManager<ModelMaps>(getContext(), mMap);
        mClusterManager.setOnClusterItemClickListener(mClusterItemClickListener);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        addItems();
    }

    private void addItems() {
        distance = new float[2];
        listLocation = new ArrayList<>();
        listLocation.clear();
        mClusterManager.clearItems();
        listLocation.add(new LatLng(10.827316269863829, 106.79021503776312));
        listLocation.add(new LatLng(10.968236781111319, 106.74283649772406));
        listLocation.add(new LatLng(10.76526305891829, 106.61237418651581));
        listLocation.add(new LatLng(10.908235588251706, 106.64739310741425));
        listLocation.add(new LatLng(10.502745464307432, 106.4867180585861));
        listLocation.add(new LatLng(10.855640641428007, 106.47023856639862));
        listLocation.add(new LatLng(10.745700155039904, 106.63022696971893));
        listLocation.add(new LatLng(10.825967423408425, 106.65219962596893));
        listLocation.add(new LatLng(11.3858050455127, 106.11773259937765));
        listLocation.add(new LatLng(11.956645507742296, 106.06934119015932));
        for (int i = 0; i < listLocation.size(); i++) {
            ModelMaps offsetItem = new ModelMaps(listLocation.get(i).latitude, listLocation.get(i).longitude, "Nhà Lầu 4 Tầng", "29" + i + " tỷ");
            if (circle != null) {
                Location.distanceBetween(offsetItem.getPosition().latitude, offsetItem.getPosition().longitude,
                        circle.getCenter().latitude, circle.getCenter().longitude, distance);
                if (distance[0] > circle.getRadius()) {
                } else {
                    mClusterManager.addItem(offsetItem);
                    mClusterManager.cluster();
                }
            } else {
                mClusterManager.addItem(offsetItem);
                mClusterManager.cluster();
            }

        }
        renderer = new CustomClusterRenderer(getContext(), mMap, mClusterManager);
        mClusterManager.setRenderer(renderer);


    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (currentLocation != null) {
        }
        getLocationUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            myLat = location.getLatitude();
            myLng = location.getLongitude();
            if (mMap != null) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(myLat, myLng))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fiber_manual_record_blue_24dp)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myLat, myLng)));
                mMap.animateCamera(CameraUpdateFactory.zoomBy(10f));
                Circle circle1 = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(myLat, myLng))
                        .radius(1000)
                        .strokeColor(Color.TRANSPARENT));
                float currentZoomLevel = getZoomLevel(circle1) + 0.5f;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(circle1.getCenter
                        (), currentZoomLevel));
            }
        }
        setUpClusterer();
        if (!addressEditor.toString().equals("")) {
            try {
                Search(addressEditor);
                BaseActivity.editorAddress.putString("addressEditor", "");
                BaseActivity.editorAddress.commit();
            } catch (Exception e) {
                BaseActivity.editorAddress.putString("addressEditor", "");
                BaseActivity.editorAddress.commit();
            }

        }
    }

    @Override
    public void onButtonData(int requestcode) {
        if (requestcode == 1) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MainActivity fragment = new MainActivity();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_top, R.anim.stay_still);
            fragmentTransaction.replace(R.id.fragment_maps, fragment);
            fragmentTransaction.addToBackStack("Maps");
            fragmentTransaction.commit();
            bottomSheetBehavior.setPeekHeight(0);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (requestcode == 2) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(myLat, myLng))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fiber_manual_record_blue_24dp)));
            Circle circle1 = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(myLat, myLng))
                    .radius(1000)
                    .strokeColor(Color.TRANSPARENT));
            float currentZoomLevel = getZoomLevel(circle1) + 0.5f;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(circle1.getCenter
                    (), currentZoomLevel));
        } else if (requestcode == 4) {
            if (circle != null) {
                circle.remove();
            }
            circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(myLat, myLng))
                    .radius(10000)
                    .strokeColor(Color.RED)
                    .fillColor(Color.TRANSPARENT));
            addItems();

        } else if (requestcode == 5) {
            if (circle != null) {
                circle.remove();
            }
            circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(myLat, myLng))
                    .radius(20000)
                    .strokeColor(Color.RED)
                    .fillColor(Color.TRANSPARENT));
            addItems();
        } else if (requestcode == 6) {
            if (circle != null) {
                circle.remove();
            }
            circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(myLat, myLng))
                    .radius(30000)
                    .strokeColor(Color.RED)
                    .fillColor(Color.TRANSPARENT));
            addItems();
        }

    }

    @Override
    public void onButtonTrueFalse(boolean blShow) {
        if (blShow == false) {
            showicon();
        } else {
            if (circle != null) {
                circle.remove();
            }
            circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(myLat, myLng))
                    .radius(100000000)
                    .strokeColor(Color.TRANSPARENT)
                    .fillColor(Color.TRANSPARENT));
            addItems();
            hideicon();
        }
    }

    @Override
    public void onEditTextSearch(String str) {
        Search(str);
    }

    @Override
    public void onFetchDataId(String str, int requestcode) {
        switch (requestcode) {
            case 1:
                List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
                mTestData.add(new HotDealObject("http://cafefcdn.com/thumb_w/650/2016/dc-0fef5-86c31-1473744680829.jpg"));
                mTestData.add(new HotDealObject("https://vneconomy.mediacdn.vn/zoom/710_400/su9ocRDG4c986hAwUXp7UpJ3Ajh6JU/Image/2017/07/tphcm-5710b.jpg"));
                mTestData.add(new HotDealObject("https://znews-photo.zadn.vn/w660/Uploaded/rugtnv/2017_08_27/0.jpg"));
                mTestData.add(new HotDealObject("http://cafefcdn.com/thumb_w/650/2017/phoi-canh-condotel-gold-coast-nha-trang-1492594553679.jpg"));
                mAdapter = new CustomViewPagerAdapter(getContext(), mTestData);
                viewPager.setAdapter(mAdapter);
                circlePageIndicator.setViewPager(viewPager);
                final float density = getResources().getDisplayMetrics().density;
                circlePageIndicator.setRadius(5 * density);
                mAdapter.notifyDataSetChanged();
                break;


        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab1:
//                presenterMaps.initButtonData(1);
                break;
            case R.id.fab2:
                presenterMaps.initButtonData(2);
                break;
            case R.id.fab3:
                presenterMaps.initButtonData(3);
                break;
            case R.id.fab_icon1:
                presenterMaps.initButtonData(4);
                break;
            case R.id.fab_icon2:
                presenterMaps.initButtonData(5);
                break;
            case R.id.fab_icon3:
                presenterMaps.initButtonData(6);
                break;
            case R.id.img_sort:
                presenterMaps.initButtonData(1);
                break;
            case R.id.btn_datlichxem:
                presenterMaps.initButtonData(7);
                break;
        }
    }

    private void getDirection(final LatLng origin, final LatLng destination, String divermode) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl
                (origin, destination, divermode), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray routesArray = response.getJSONArray("routes");
                    JSONObject object = routesArray.getJSONObject(0);
                    JSONObject overviewPolylines = object.getJSONObject("overview_polyline");
                    String encodedString = overviewPolylines.getString("points");
                    List<LatLng> list = decodePoly(encodedString);
                    if (line != null) {
                        line.remove();
                    }
                    line = mMap.addPolyline(new PolylineOptions()
                            .addAll(list)
                            .width(5)
                            .color(getResources().getColor(R.color.colorPrimaryDark))//Google maps
                            // blue color
                            .geodesic(true)
                    );

                    JSONArray legs = object.getJSONArray("legs");
                    JSONObject objectSteps = legs.getJSONObject(0);
                    JSONArray steps = objectSteps.getJSONArray("steps");
                    setStepListview(steps, origin, destination);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private String getRequestUrl(LatLng origin, LatLng destination, String diverMode) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + destination.latitude + "," + destination.longitude;
        String sensor = "sensor=false";
        String mode = "mode=driving";
        String language = "language=vi";
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode + "&" +
                language +
                "&key=" +
                getResources().getString(R.string.google_maps_key);
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }

    private void setStepListview(JSONArray steps, LatLng origin, LatLng destination) {
        wScreen = getResources().getDisplayMetrics().widthPixels;
        hScreen = getResources().getDisplayMetrics().heightPixels;
        padding = (int) (wScreen * 0.10);
        builder = new LatLngBounds.Builder();
        builder.include(origin);
        builder.include(destination);
        bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, wScreen, hScreen,
                padding));
    }


}
