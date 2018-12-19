package vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.AdapterThamdinh;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPending;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPendingData;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Presenter.PresenterThamdinh.PresenterReponsetoViewThamdinh;
import vn.edu.poly.realestate.Presenter.PresenterThamdinh.PresenterThamdinh;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.Menu.MenuActivity;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.Details.DetailsChoThamDinhActivity;

public class ThamDinhActivity extends BaseActivity implements PresenterReponsetoViewThamdinh, View.OnClickListener {
    ListView lst_thamdinh_menu;
    PresenterThamdinh presenterThamdinh;
    TextView txt_title_post;
    ImageView img_back_post;
    private ShimmerFrameLayout mShimmerViewContainer, mShimmerViewContainer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tham_dinh);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        lst_thamdinh_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContructorPendingData contructor
                        = (ContructorPendingData) parent.getItemAtPosition(position);
                presenterThamdinh.IntentDataDetails(contructor);
            }
        });
        img_back_post.setOnClickListener(this);
    }

    private void initData() {
        presenterThamdinh = new PresenterThamdinh(this, this);
        mShimmerViewContainer1.startShimmer();
        mShimmerViewContainer.startShimmer();
        presenterThamdinh.initFetchData();
        txt_title_post.setText(getResources().getString(R.string.txt_danhsachchothamdinh));

    }

    private void initControl() {
        mShimmerViewContainer1 = findViewById(R.id.shimmer_view_container1);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        img_back_post = findViewById(R.id.img_back_post);
        txt_title_post = findViewById(R.id.txt_title_post);
        lst_thamdinh_menu = findViewById(R.id.lst_thamdinh_menu);
    }

    @Override
    public void onFetchData(AdapterThamdinh adapter) {
        lst_thamdinh_menu.setAdapter(adapter);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransitionoad);
        lst_thamdinh_menu.startAnimation(animation);
        adapter.notifyDataSetChanged();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        mShimmerViewContainer1.stopShimmer();
        mShimmerViewContainer1.setVisibility(View.GONE);
    }

    @Override
    public void onIntent() {

    }

    @Override
    public void onIntentDataDetails() {

    }

    @Override
    public void onBackPressed() {
        presenterThamdinh.initIntent(0);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_post:
                onBackPressed();
                break;
        }
    }
}
