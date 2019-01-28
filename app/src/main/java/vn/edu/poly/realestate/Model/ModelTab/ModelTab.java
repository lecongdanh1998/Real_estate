package vn.edu.poly.realestate.Model.ModelTab;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.InfoAccount.InfoaccountActivity;
import vn.edu.poly.realestate.View.MapsActivity.MapsActivity;
import vn.edu.poly.realestate.View.Notifation.NotifationActivity;
import vn.edu.poly.realestate.View.TinDang.TinDangActivity;

public class ModelTab {
    ModelReponsetoPresenterTab callback;
    Context context;
    Activity activity;
    Fragment fragment;
    public ModelTab(ModelReponsetoPresenterTab callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }


    public void initFragment(int requestcode){
        switch (requestcode){
            case 0:
                fragment = new MapsActivity();
                callback.onFetchFragment(fragment,"Tab");
                break;
            case 1:
                fragment = new TinDangActivity();
                callback.onFetchFragment(fragment,"TinDang");
                break;
            case 2:
                fragment = new NotifationActivity();
                callback.onFetchFragment(fragment,"Notifation");
                break;
            case 3:
                fragment = new InfoaccountActivity();
                callback.onFetchFragment(fragment,"Account");
                break;
        }

    }





}
