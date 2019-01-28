package vn.edu.poly.realestate.Model.ModelTinDang;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Infodatadeposit;

public class ModelTinDang {
    ModelReponsetoPresenterTinDang callback;
    Context context;
    Activity activity;
    ListViewMainActivityAdapter adapter;
    private View.OnClickListener clickNe;
    public ModelTinDang(ModelReponsetoPresenterTinDang callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }
    public void initFetchData(){
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status", "APPROVAL");
        final Call<Infodatadeposit> callback1 = dataClient.Infodatadeposit(hashMap);
        callback1.enqueue(new Callback<Infodatadeposit>() {
            @Override
            public void onResponse(Call<Infodatadeposit> call, Response<Infodatadeposit> response) {
                if (response.body() == null) {
                } else {
                    adapter = new ListViewMainActivityAdapter(response.body().getData(), context, clickNe);
                    callback.onFecthDataAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Infodatadeposit> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
