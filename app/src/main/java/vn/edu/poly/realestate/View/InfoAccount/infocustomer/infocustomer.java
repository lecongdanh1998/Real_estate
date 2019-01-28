package vn.edu.poly.realestate.View.InfoAccount.infocustomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.edu.poly.realestate.Presenter.PresenterInfo.PresenterInfo;
import vn.edu.poly.realestate.Presenter.PresenterInfo.PresenterReponsetoViewInfo;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.DangTinBatDongSan.DangTinBatDongSanActivity;

public class infocustomer extends Fragment implements View.OnClickListener, PresenterReponsetoViewInfo {
    private View view;
    TextView txt_dangtinbatdongsan;
    PresenterInfo presenterInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_infocustomer, container, false);
        initControl();
        initData();
        initEvent();
        initOnClick();
        return view;
    }

    private void initEvent() {
    }

    private void initOnClick() {
        txt_dangtinbatdongsan.setOnClickListener(this);
    }

    private void initControl() {
        txt_dangtinbatdongsan = view.findViewById(R.id.txt_dangtinbatdongsan);
    }

    private void initData() {
        presenterInfo = new PresenterInfo(this, getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_dangtinbatdongsan:
                presenterInfo.initButtonItent(1);
                break;
        }
    }

    @Override
    public void onTabLayout() {

    }

    @Override
    public void onButtonIntent(int requestcode) {
        switch (requestcode) {
            case 1:
                initIntentView(DangTinBatDongSanActivity.class);
                break;
        }
    }

    private void initIntentView(Class c){
        Intent intent = new Intent(getContext(),c);
        startActivity(intent);
    }


}
