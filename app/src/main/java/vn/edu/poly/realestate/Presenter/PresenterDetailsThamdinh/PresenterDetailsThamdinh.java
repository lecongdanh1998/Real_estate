package vn.edu.poly.realestate.Presenter.PresenterDetailsThamdinh;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.realestate.Adapter.AdapterDetailsThamdinhUserRating;
import vn.edu.poly.realestate.Model.ModelDetailsThamdinh.ModelDetailsThamdinh;
import vn.edu.poly.realestate.Model.ModelDetailsThamdinh.ModelReponsetoPresenterThamdinh;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.ThamDinhActivity;

public class PresenterDetailsThamdinh implements ModelReponsetoPresenterThamdinh {
    PresenterReponsetoViewDetailsThamdinh callback;
    Context context;
    Activity activity;
    ModelDetailsThamdinh modelDetailsThamdinh;

    public PresenterDetailsThamdinh(PresenterReponsetoViewDetailsThamdinh callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelDetailsThamdinh = new ModelDetailsThamdinh(this, context);
    }

    public void initIntent(int requestcode) {
        modelDetailsThamdinh.initIntent(requestcode);
    }

    public void dialogSubmit() {
        modelDetailsThamdinh.dialogSubmit();
    }

    public void initFetchData() {
        modelDetailsThamdinh.initFetchData();
    }

    public void initFetcDataThamdinh(float rating1, String extBuy, String extSell) {
        modelDetailsThamdinh.initDialogData(rating1, extBuy, extSell);
    }

    public void initFetchDataId() {
        modelDetailsThamdinh.initInfoDataUser();
    }
    public void initButtonDuyettinDang(){
        modelDetailsThamdinh.initDialogDataDuyet();
    }


    @Override
    public void onIntent() {
        callback.onIntent();
    }

    @Override
    public void onDialogSubmit() {
        callback.onDialogSubmit();
    }

    @Override
    public void onFetchDataId(String str, int requestcode) {
        callback.onFetchDataId(str, requestcode);
    }

    @Override
    public void onFetchDataThamdinh(String str, int requestcode) {
        callback.onFetchDataThamdinh(str, requestcode);
    }

    @Override
    public void onFetchDataId(AdapterDetailsThamdinhUserRating adapterDetailsThamdinhUserRating, float rating, int priceBuy, int priceSell) {
        callback.onFetchDataId(adapterDetailsThamdinhUserRating, rating, priceBuy, priceSell);
    }

    @Override
    public void onButton(int requestcode) {
        callback.onButton(requestcode);
    }

    @Override
    public void onButtonDuyetTinDang(String str) {
        callback.onButtonDuyetTinDang(str);
    }
}
