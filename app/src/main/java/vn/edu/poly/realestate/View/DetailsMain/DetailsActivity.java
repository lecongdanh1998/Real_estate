package vn.edu.poly.realestate.View.DetailsMain;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.realestate.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.HotDealObject;
import vn.edu.poly.realestate.Presenter.PresenterDetails.PresenterDetails;
import vn.edu.poly.realestate.Presenter.PresenterDetails.PresenterReponsetoViewDetails;
import vn.edu.poly.realestate.R;

public class DetailsActivity extends BaseActivity implements PresenterReponsetoViewDetails,
        View.OnClickListener {
    View view;
    Button btn_datlichxem;
    private double latitude, longitude, myLat, myLng;
    ImageView img_back_details;
    private GoogleMap mMap;
    ScrollView scrollView;
    private PresenterDetails presenterDetails;
    ViewPager viewPager;
    private static final int MY_LOCATION_REQUEST_CODE = 1000;
    private GoogleApiClient googleApiClient;
    CustomViewPagerAdapter mAdapter;
    CirclePageIndicator circlePageIndicator;
    private Location currentLocation;
    LinearLayout linearLayout,heighline;
    private Handler handler;
    private final int delay = 2500;
    int heightLin,showhide;
    private int pageQuang = 0;
    private LocationRequest locationRequest;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initControl();
        initData();
        initOnClick();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack("Maps", 0);
    }

    private void initOnClick() {
        img_back_details.setOnClickListener(this);
        btn_datlichxem.setOnClickListener(this);

    }

    @SuppressLint("RestrictedApi")
    private void initData() {
        presenterDetails = new PresenterDetails(this, this);
        presenterDetails.initFetchData();
        handler = new Handler();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }


    private void initControl() {
        viewPager = findViewById(R.id.pager);
        circlePageIndicator = findViewById(R.id.indicator);
        img_back_details = findViewById(R.id.img_back_details);
        btn_datlichxem = findViewById(R.id.btn_datlichxem);
        linearLayout = findViewById(R.id.linearLayout);
        heighline = findViewById(R.id.heighline);
        scrollView = findViewById(R.id.scrollView);
    }


    @Override
    public void onDetailsFetchDataSuccess(String text, int requestCodeTextview) {

    }

    @Override
    public void onBack() {

    }

    @Override
    public void onShowDialogHelp() {

    }

    @Override
    public void onFetchDataId(String images, int requestcode) {
        switch (requestcode) {
            case 1:
                List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
                JSONArray cast = null;
                try {
                    cast = new JSONArray(images);
                    for (int i = 0; i < cast.length(); i++) {
                        String street = cast.getString(i);
                        mTestData.add(new HotDealObject(street));
                    }
                    mAdapter = new CustomViewPagerAdapter(this, mTestData);
                    viewPager.setAdapter(mAdapter);
                    circlePageIndicator.setViewPager(viewPager);
                    final float density = getResources().getDisplayMetrics().density;
                    circlePageIndicator.setRadius(5 * density);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    //
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_datlichxem:
                presenterDetails.onBack(2);
                break;
            case R.id.img_back_details:
                onBackPressed();
                break;
        }
    }
}
