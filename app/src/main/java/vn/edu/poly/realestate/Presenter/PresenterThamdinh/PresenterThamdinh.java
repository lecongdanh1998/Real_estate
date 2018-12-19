package vn.edu.poly.realestate.Presenter.PresenterThamdinh;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.realestate.Adapter.AdapterThamdinh;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPendingData;
import vn.edu.poly.realestate.Model.ModelThamDinh.ModelReponsetoPresenterThamdinh;
import vn.edu.poly.realestate.Model.ModelThamDinh.ModelThamdinh;

public class PresenterThamdinh implements ModelReponsetoPresenterThamdinh {
    PresenterReponsetoViewThamdinh callback;
    Context context;
    Activity activity;
    ModelThamdinh modelThamdinh;
    public PresenterThamdinh(PresenterReponsetoViewThamdinh callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelThamdinh = new ModelThamdinh(this,context);
    }


    public void IntentDataDetails(ContructorPendingData contructor) {
        modelThamdinh.IntentDataDetails(contructor);
    }


    public void initFetchData(){
        modelThamdinh.initFetchData();
    }
    public void initIntent(int requestcode){
        modelThamdinh.initIntent(requestcode);
    }

    @Override
    public void onFetchData(AdapterThamdinh adapter) {
        callback.onFetchData(adapter);
    }

    @Override
    public void onIntent() {
        callback.onIntent();
    }

    @Override
    public void onIntentDataDetails() {
        callback.onIntentDataDetails();
    }
}
