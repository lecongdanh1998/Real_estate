package vn.edu.poly.realestate.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Presenter.PresenterMain.PresenterMain;
import vn.edu.poly.realestate.Presenter.PresenterMain.PresenterReponsetoViewMain;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.DetailsMain.DetailsActivity;
import vn.edu.poly.realestate.View.DetailsMain.DetailsMainActivity;
import vn.edu.poly.realestate.View.MapsActivity.MapsActivity;
import vn.edu.poly.realestate.View.User.WalletActivity;

public class MainActivity extends Fragment implements View.OnClickListener, PresenterReponsetoViewMain {
    ListView listView;
    String screen;
    ImageView img_wallet_MainActivity, img_question_Mainactivity,img_filter;
    PresenterMain presenterMain;
    int positionListview;
    private ShimmerFrameLayout mShimmerViewContainer, mShimmerViewContainer1;
    int statusInternet;
    private Handler handler;
    private final int delay = 2000;
    View view;
    CardView cardview;
    Runnable runnable = new Runnable() {
        public void run() {
            if (statusInternet == 1) {
                handler.removeCallbacks(runnable);
            } else if (statusInternet == 0) {
                onStart();
                handler.postDelayed(this, delay);
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_main, container, false);
        initControl();
        initSharedPre();
        initData();
        initOnClick();
        return view;

    }



    private void initSharedPre() {
        screen = BaseActivity.dataLoginScreen.getString("ScreenMain", "");


    }

    @Override
    public void onStart() {
        super.onStart();
        statusInternet = BaseActivity.dataInternet.getInt("status", 0);
        if (statusInternet == 1) {
            presenterMain.ReceivedHanleData();
            handler.removeCallbacks(runnable);
        } else if (statusInternet == 0) {
            handler.postDelayed(runnable, delay);
        }
    }

    @SuppressLint("NewApi")
    private void initOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Data contructor
                        = (Data) parent.getItemAtPosition(position);
                presenterMain.IntentData(contructor, position);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                positionListview = firstVisibleItem;
            }
        });
        img_filter.setOnClickListener(this);

    }

    private void intentView(Class c) {
        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
        if (screen.toString().equals("1")) {

        } else {

        }
//        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
    }

    private void initControl() {
        mShimmerViewContainer1 = view.findViewById(R.id.shimmer_view_container1);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        listView = view.findViewById(R.id.lst_MainActivity);
        cardview = getActivity().findViewById(R.id.cardview);
        img_filter = view.findViewById(R.id.img_filter);
    }

    private void initData() {
        presenterMain = new PresenterMain(this, getContext(), getActivity());
        handler = new Handler();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer1.startShimmer();
        mShimmerViewContainer.startShimmer();
        if (statusInternet == 1) {
            handler.removeCallbacks(runnable);
        } else if (statusInternet == 0) {
//            handler.postDelayed(runnable, delay);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer1.stopShimmer();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cardview.setVisibility(View.VISIBLE);
    }

    //    @Override
//    public void onBackPressed() {
//        presenterMain.Exit(positionListview);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_filter:
                presenterMain.initButtonIntent(2);
                break;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onFecthDataAdapter(ListViewMainActivityAdapter adapter) {
        positionListview = BaseActivity.dataLoginInfo.getInt("position", 3);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.mytransitionoad);
        listView.startAnimation(animation);
        listView.setAdapter(adapter);
        listView.setSelection(positionListview);
        adapter.notifyDataSetChanged();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        mShimmerViewContainer1.stopShimmer();
        mShimmerViewContainer1.setVisibility(View.GONE);
    }

    @Override
    public void onIntentData() {
        intentView(DetailsActivity.class);
    }

    @Override
    public void onFetchLogout() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void onShowDialogHelp() {

    }

    @Override
    public void onButtonIntent() {

    }

    @Override
    public void onAddress(String address) {
        BaseActivity.editorAddress = BaseActivity.dataAddress.edit();
        BaseActivity.editorAddress.putString("addressEditor",address);
        BaseActivity.editorAddress.commit();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapsActivity fragment = new MapsActivity();
        fragmentTransaction.replace(R.id.fragment_main, fragment);
        fragmentTransaction.addToBackStack("MainActivity");
        fragmentTransaction.commit();
//        Toast.makeText(getContext(), address, Toast.LENGTH_SHORT).show();
    }


}
