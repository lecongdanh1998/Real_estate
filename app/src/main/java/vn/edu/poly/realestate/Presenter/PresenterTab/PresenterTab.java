package vn.edu.poly.realestate.Presenter.PresenterTab;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import vn.edu.poly.realestate.Model.ModelTab.ModelReponsetoPresenterTab;
import vn.edu.poly.realestate.Model.ModelTab.ModelTab;

public class PresenterTab implements ModelReponsetoPresenterTab {
    PresenterReponsetoViewTab callback;
    ModelTab modelTab;
    Context context;
    Activity activity;

    public PresenterTab(PresenterReponsetoViewTab callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelTab = new ModelTab(this,context);
    }

    public void initFragment(int requestcode){
        modelTab.initFragment(requestcode);
    }

    @Override
    public void onFetchFragment(Fragment fragment, String str) {
        callback.onFetchFragment(fragment,str);
    }
}
