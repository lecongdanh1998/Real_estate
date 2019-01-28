package vn.edu.poly.realestate.Presenter.PresenterDangTinBatDongSan;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.Spinner;

import vn.edu.poly.realestate.Adapter.AdapterFilterChonhuong;
import vn.edu.poly.realestate.Adapter.AdapterFilterGiayChuQuyen;
import vn.edu.poly.realestate.Adapter.AdapterFilterLoaiDat;
import vn.edu.poly.realestate.Adapter.AdapterFilterPhong;
import vn.edu.poly.realestate.Model.ModelDangTinBatDongSan.ModelDangTinBatDongSan;
import vn.edu.poly.realestate.Model.ModelDangTinBatDongSan.ModelReponsetoPresenterDangTinBatDongSan;
import vn.edu.poly.realestate.Model.ModelPost.ModelPost;
import vn.edu.poly.realestate.Presenter.PresenterPost.PresenterReponsetoViewPost;

public class PresenterDangTinBatDongSan implements ModelReponsetoPresenterDangTinBatDongSan {
    PresenterReponsetoViewDangTinBatDongSan callback;
    Context context;
    Activity activity;
    ModelDangTinBatDongSan modelPost;

    public PresenterDangTinBatDongSan(PresenterReponsetoViewDangTinBatDongSan callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelPost = new ModelDangTinBatDongSan(this, context);
    }

    public void initSpinnerAddress(Spinner spin_Wards, Spinner spin_District, Spinner spin_city) {
        modelPost.initSpinnerAddress(spin_Wards, spin_District, spin_city);
    }

    public void initEditSearch(final String search) {
        modelPost.initEditSearch(search);
    }

    public void initFindDataAddress(String edt_find, String spinnerCity, String spinnerDictric, String spinnerWard){
        modelPost.initFindDataAddress(edt_find,spinnerCity,spinnerDictric,spinnerWard);
    }

    public void initDataChonhuong() {
        modelPost.initDataChonhuong();
    }

    public void initDataLoaiDat(){
        modelPost.initDataLoaiDat();
    }
    public void initDataPhong(){
        modelPost.initDataPhong();
    }
    public void initDataGiayChuQuyen(){
        modelPost.initDataGiayChuQuyen();
    }
    public void initEditTextPrice(EditText edt_gia, EditText edt_giathuongluong, EditText edt_giacoc){
        modelPost.initEditTextPrice(edt_gia,edt_giathuongluong,edt_giacoc);
    }
    public void initOnKeyBoard(CardView cardView){
        modelPost.initOnKeyBoard(cardView);
    }

    @Override
    public void onIntentView() {
        callback.onIntentView();
    }

    @Override
    public void onSpinnerAddress(String requestAddress, int requestcode) {
        callback.onSpinnerAddress(requestAddress, requestcode);
    }

    @Override
    public void onDataChonhuong(AdapterFilterChonhuong adapterFilterChonhuong) {
        callback.onDataChonhuong(adapterFilterChonhuong);
    }

    @Override
    public void onDataLoaiDat(AdapterFilterLoaiDat adapterFilterLoaiDat) {
        callback.onDataLoaiDat(adapterFilterLoaiDat);
    }

    @Override
    public void onDataPhong(AdapterFilterPhong adapterFilterPhong) {
        callback.onDataPhong(adapterFilterPhong);
    }

    @Override
    public void onDataGiayChuQuyen(AdapterFilterGiayChuQuyen adapterFilterGiayChuQuyen) {
        callback.onDataGiayChuQuyen(adapterFilterGiayChuQuyen);
    }

    @Override
    public void onFetchAddress(String str) {
        callback.onFetchAddress(str);
    }

    @Override
    public void onEditTextSearch(String str) {
        callback.onEditTextSearch(str);
    }

    @Override
    public void onKeyboard() {
        callback.onKeyboard();
    }


}
