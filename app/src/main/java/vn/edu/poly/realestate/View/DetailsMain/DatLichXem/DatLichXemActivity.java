package vn.edu.poly.realestate.View.DetailsMain.DatLichXem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterDatLichXem.PresenterReponsetoViewDatLichXem;
import vn.edu.poly.realestate.R;

public class DatLichXemActivity extends BaseActivity implements PresenterReponsetoViewDatLichXem, View.OnClickListener {
    ImageView img_back_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lich_xem);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        img_back_details.setOnClickListener(this);
    }

    private void initData() {
    }

    private void initControl() {
        img_back_details = findViewById(R.id.img_back_details);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_details:
                onBackPressed();
                break;
        }
    }
}
