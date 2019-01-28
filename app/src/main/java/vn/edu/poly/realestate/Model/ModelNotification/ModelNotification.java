package vn.edu.poly.realestate.Model.ModelNotification;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.AdapterNotification;

public class ModelNotification {
    ModelReponsetoPresenterNotification callback;
    Context context;
    Activity activity;
    ArrayList<ModelContructor> arrayList;
    AdapterNotification adapter;

    public ModelNotification(ModelReponsetoPresenterNotification callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        arrayList = new ArrayList<>();
    }

    public void initFetchData() {
        for (int i = 0; i < 10; i++) {
            arrayList.add(new ModelContructor("https://assets1.ignimgs.com/2018/11/30/aquaman-1280-1543622354275_960w.jpg"
                    , "Anh/Chị đã đăng ký huấn luyện 2019/01/11 17:30:00.0 tại Head - Lê Đại Hành - Tầng 4"
                    , "09:51 - 17/12/2018"));
        }
        adapter = new AdapterNotification(context, arrayList);
        callback.onFetchData(adapter);
    }

}
