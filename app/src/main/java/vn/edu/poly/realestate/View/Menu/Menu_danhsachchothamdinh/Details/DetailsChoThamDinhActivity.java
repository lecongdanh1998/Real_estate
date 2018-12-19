package vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.Details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.realestate.Adapter.AdapterDetailsThamdinhUserRating;
import vn.edu.poly.realestate.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.HotDealObject;
import vn.edu.poly.realestate.Presenter.PresenterDetailsThamdinh.PresenterDetailsThamdinh;
import vn.edu.poly.realestate.Presenter.PresenterDetailsThamdinh.PresenterReponsetoViewDetailsThamdinh;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.ThamDinhActivity;

public class DetailsChoThamDinhActivity extends BaseActivity implements View.OnClickListener, PresenterReponsetoViewDetailsThamdinh {
    AppBarLayout appBar;
    Toolbar toolbar;
    ImageView img_back_member;
    Button btn_Submit_danhgia, btn_Submit_duyet;
    PresenterDetailsThamdinh presenterDetailsThamdinh;
    private ViewPager mViewPage;
    private CustomViewPagerAdapter mAdapter;
    private Handler handler;
    RatingBar rb_rating_details;
    private final int delay = 2500;
    TextView txt_priceSell, txt_priceBuy;
    private int pageQuang = 0;
    ListView listView;
    LinearLayout LNTlayoutDetails;
    TextView txtTile, txtAddress, txtSell, txtBuy, txtDeposit;
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
        setContentView(R.layout.activity_details_cho_tham_dinh);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        img_back_member.setOnClickListener(this);
        btn_Submit_danhgia.setOnClickListener(this);
        btn_Submit_duyet.setOnClickListener(this);
    }

    private void initData() {
        presenterDetailsThamdinh = new PresenterDetailsThamdinh(this, this);
        presenterDetailsThamdinh.initFetchData();
        presenterDetailsThamdinh.initFetchDataId();
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    Animation animation = AnimationUtils.loadAnimation(DetailsChoThamDinhActivity.this, R.anim.mytransitionoad500);
                    toolbar.startAnimation(animation);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorTextBlue));                    // Collapsed
                } else if (verticalOffset == 0) {
                    // Expanded
                    Animation animation = AnimationUtils.loadAnimation(DetailsChoThamDinhActivity.this, R.anim.mytransitionoad500);
                    toolbar.startAnimation(animation);
                    toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                } else {
                    // Somewhere in between
                }
            }
        });
        handler = new Handler();
    }

    private void initControl() {
        LNTlayoutDetails = findViewById(R.id.LNTlayoutDetails);
        btn_Submit_duyet = findViewById(R.id.btn_Submit_duyet);
        txt_priceSell = findViewById(R.id.txt_priceSell);
        txt_priceBuy = findViewById(R.id.txt_priceBuy);
        rb_rating_details = findViewById(R.id.rb_rating_details);
        listView = findViewById(R.id.lst_details_Thamdinh);
        img_back_member = findViewById(R.id.img_back_member);
        appBar = findViewById(R.id.appBar);
        mViewPage = (ViewPager) findViewById(R.id.viewPager_details);
        toolbar = findViewById(R.id.toolbar);
        btn_Submit_danhgia = findViewById(R.id.btn_Submit_danhgia);
        txtTile = findViewById(R.id.txt_title_details);
        txtAddress = findViewById(R.id.txt_address_details);
        txtSell = findViewById(R.id.txt_sell_details);
        txtBuy = findViewById(R.id.txt_buy_details);
        txtDeposit = findViewById(R.id.txt_deposit_details);
    }

    @Override
    public void onBackPressed() {
        presenterDetailsThamdinh.initIntent(0);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Submit_danhgia:
                presenterDetailsThamdinh.dialogSubmit();
                break;
            case R.id.img_back_member:
                onBackPressed();
                break;
            case R.id.btn_Submit_duyet:
                presenterDetailsThamdinh.initButtonDuyettinDang();
                break;
        }
    }

    @Override
    public void onIntent() {

    }

    @Override
    public void onDialogSubmit() {

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


    @Override
    public void onFetchDataId(String str, int requestcode) {
        if (requestcode == 0) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        } else if (requestcode == 1) {
            List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
            JSONArray cast = null;
            try {
                cast = new JSONArray(str);
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

        } else if (requestcode == 2) {
            txtAddress.setText(str);

        } else if (requestcode == 3) {
            txtBuy.setText(str);

        } else if (requestcode == 4) {
            txtDeposit.setText(str);

        } else if (requestcode == 5) {
            txtSell.setText(str);

        } else if (requestcode == 6) {
            txtTile.setText(str);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransitionoad1000);
            LNTlayoutDetails.startAnimation(animation);
            LNTlayoutDetails.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFetchDataThamdinh(String str, int requestcode) {
        if (requestcode == 0) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        } else if (requestcode == 1) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFetchDataId(AdapterDetailsThamdinhUserRating adapterDetailsThamdinhUserRating, float rating, int priceBuy, int priceSell) {
        listView.setAdapter(adapterDetailsThamdinhUserRating);
        setListViewHeightBasedOnChildren(listView);
        adapterDetailsThamdinhUserRating.notifyDataSetChanged();
        rb_rating_details.setRating(rating);
        txt_priceBuy.setText(String.valueOf(priceBuy) + " Xu");
        txt_priceSell.setText(String.valueOf(priceSell) + " Xu");
    }

    @Override
    public void onButton(int requestcode) {
        if (requestcode == 0) {
            btn_Submit_danhgia.setEnabled(false);
            btn_Submit_danhgia.setAlpha((float) 0.5);
        } else if (requestcode == 1) {
            btn_Submit_danhgia.setEnabled(true);
            btn_Submit_danhgia.setAlpha(1);
        } else if (requestcode == 2) {
            btn_Submit_duyet.setEnabled(true);
            btn_Submit_duyet.setAlpha(1);
        } else if (requestcode == 3) {
            btn_Submit_duyet.setEnabled(false);
            btn_Submit_duyet.setAlpha((float) 0.5);
        }
    }

    @Override
    public void onButtonDuyetTinDang(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = (ListAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
