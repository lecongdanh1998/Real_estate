package vn.edu.poly.realestate.Presenter.PresenterPost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.AdapterPostAddress;
import vn.edu.poly.realestate.Model.ModelPost.ModelPost;
import vn.edu.poly.realestate.Model.ModelPost.ModelReponsetoPresenterPost;
import vn.edu.poly.realestate.Model.PostAddressContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataProvincesDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.UpLoadImage.InfodataUploadImage;
import vn.edu.poly.realestate.View.Menu.MenuActivity;

public class PresenterPost implements ModelReponsetoPresenterPost {
    PresenterReponsetoViewPost callback;
    Context context;
    Activity activity;
    ModelPost modelPost;

    public PresenterPost(PresenterReponsetoViewPost callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelPost = new ModelPost(this, context);
    }

    public void initIntentView(int requestcode) {
        modelPost.initIntentView(requestcode);
    }

    public void initSpinnerAddress(Spinner spin_Wards, Spinner spin_District, Spinner spin_city) {
        modelPost.initSpinnerAddress(spin_Wards, spin_District, spin_city);
    }

    public void initEditTextArea(EditText edt_user_arearong, EditText edt_user_areadai) {
        modelPost.initEditTextArea(edt_user_arearong, edt_user_areadai);
    }

    public void initEditTextPrice(EditText edt_price_buy, EditText edt_price_deposit,
                                  EditText edt_price_sell,
                                  EditText edt_title,
                                  EditText edt_address,
                                  EditText edt_describe) {
        modelPost.initEditTextPrice(edt_price_buy, edt_price_deposit, edt_price_sell,edt_title,edt_address,edt_describe);
    }
    public void initUpLoadImages(final String id,String image) {
        modelPost.initUpLoadImages(id,image);
    }



    public void SDK_Camera() {
        modelPost.SDK_Camera();
    }

    public void initInsert(String title, String address, String ward, String district, String city,
                           String area, String price_buy, String price_deposit, String price_sell,
                           String description,
                           String image) {
        modelPost.initInsert(title, address, ward, district, city, area, price_buy, price_deposit,
                price_sell, description, image);
    }


    public void galleryUpload() {
        modelPost.galleryUpload();
    }


    @Override
    public void onIntentView() {
        callback.onIntentView();
    }

    @Override
    public void onSpinnerAddress(int requestAddress,int requestcode) {
        callback.onSpinnerAddress(requestAddress,requestcode);
    }

    @Override
    public void onEditTextArea(String area,int requestcode) {
        callback.onEditTextArea(area,requestcode);
    }

    @Override
    public void onEditTextPrice(String error,int requestcode) {
        callback.onEditTextPrice(error,requestcode);
    }

    @Override
    public void onFetchCamera(Intent takePictureIntent, int camera) {
        callback.onFetchCamera(takePictureIntent, camera);
    }

    @Override
    public void onFetchGallery(Intent imageDownload) {
        callback.onFetchGallery(imageDownload);
    }

    @Override
    public void onFetchInsertData(String str,String error,int requestcode) {
        callback.onFetchInsertData(str,error,requestcode);
    }

    @Override
    public void onFetchUpLoad(String str) {
        callback.onFetchUpLoad(str);
    }
}
