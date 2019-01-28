package vn.edu.poly.realestate.Presenter.PresenterNotification;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.realestate.Adapter.AdapterNotification;
import vn.edu.poly.realestate.Model.ModelNotification.ModelNotification;
import vn.edu.poly.realestate.Model.ModelNotification.ModelReponsetoPresenterNotification;


public class PresenterNotification implements ModelReponsetoPresenterNotification {
    PresenterReponsetoViewNotification callback;
    Context context;
    Activity activity;
    ModelNotification modelNotification;

    public PresenterNotification(PresenterReponsetoViewNotification callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelNotification = new ModelNotification(this,context);
    }

    public void initFetchData(){
        modelNotification.initFetchData();
    }

    @Override
    public void onFetchData(AdapterNotification adapter) {
        callback.onFetchData(adapter);
    }
}
