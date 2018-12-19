package vn.edu.poly.realestate.Model.ModelThamDinh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.AdapterThamdinh;
import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPending;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPendingData;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Infodatadeposit;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingData;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.MenuActivity;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.Details.DetailsChoThamDinhActivity;

public class ModelThamdinh {
    ModelReponsetoPresenterThamdinh callback;
    Context context;
    Activity activity;
    AdapterThamdinh adapterThamdinh;

    public ModelThamdinh(ModelReponsetoPresenterThamdinh callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }

    public void initFetchData() {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status", "PENDING");
        final Call<ContructorPending> callback1 = dataClient.InfodataDanhsachThamdinh(hashMap);
        callback1.enqueue(new Callback<ContructorPending>() {
            @Override
            public void onResponse(Call<ContructorPending> call, Response<ContructorPending> response) {
                ArrayList<ContructorPendingData> contructorPendingData = response.body().getData();
                if (response.body() == null) {
                } else {
                    adapterThamdinh = new AdapterThamdinh(contructorPendingData, context);
                    callback.onFetchData(adapterThamdinh);
                }
            }

            @Override
            public void onFailure(Call<ContructorPending> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initIntent(int requestcode) {
        if (requestcode == 0) {
            intentView(MainActivity.class, 0);
        } else if (requestcode == 1) {
            intentView(DetailsChoThamDinhActivity.class, 1);
        }
        callback.onIntent();
    }

    public void IntentDataDetails(ContructorPendingData contructor) {
        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
        BaseActivity.editorInfo.putString("idDetailsThamdinh", String.valueOf(contructor.getId()));
        BaseActivity.editorInfo.commit();
        initIntent(1);
        callback.onIntentDataDetails();
    }


    private void intentView(Class c, int requestcode) {
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
        if (requestcode == 0) {
            activity.overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
        } else if (requestcode == 1) {
            activity.overridePendingTransition(R.anim.enter_from_right, R.anim.stay_still);
        }
        activity.finish();
    }

}
