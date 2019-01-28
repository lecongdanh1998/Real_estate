package vn.edu.poly.realestate.Presenter.PresenterInfo;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.realestate.Model.ModelInfo.ModelReponsetoPresenterInfo;
import vn.edu.poly.realestate.Model.ModelInfo.Modelinfor;
import vn.edu.poly.realestate.Presenter.PresenterUser.SignIn.PresenterReponsetoViewSignIn;

public class PresenterInfo implements ModelReponsetoPresenterInfo {
    PresenterReponsetoViewInfo callback;
    Context context;
    Activity activity;
    Modelinfor modelinfor;
    public PresenterInfo(PresenterReponsetoViewInfo callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelinfor = new Modelinfor(this,context);
    }

    public void initButtonItent(int requestcode){
        modelinfor.initButtonItent(requestcode);
    }



    @Override
    public void onTabLayout() {
        callback.onTabLayout();
    }

    @Override
    public void onButtonIntent(int requestcode) {
        callback.onButtonIntent(requestcode);
    }
}
