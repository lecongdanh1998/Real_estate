package vn.edu.poly.realestate.Model.ModelInfo;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.AdapterTabLayoutFrangment;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.InfoAccount.historycoins.historycoins;
import vn.edu.poly.realestate.View.InfoAccount.historycustomer.historycustomer;
import vn.edu.poly.realestate.View.InfoAccount.infocustomer.infocustomer;

public class Modelinfor {
    ModelReponsetoPresenterInfo callback;
    Context context;
    Activity activity;
    public Modelinfor(ModelReponsetoPresenterInfo callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }

    public void initButtonItent(int requestcode){
        callback.onButtonIntent(requestcode);
    }

}
