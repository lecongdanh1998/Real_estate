package vn.edu.poly.realestate.Model.ModelFilter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.AdapterFilterChonhuong;
import vn.edu.poly.realestate.Adapter.AdapterPostAddressDictrict;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddressDictrict;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataDistrictsDropdown;

public class ModelFilter {
    ModelReponsetoPresenterFilter callback;
    Context context;
    AdapterPostAddressDictrict arrayAdapter_District;
    Activity activity;
    ArrayList<DataAddressDictrict> dataAddressDictricts;
    ArrayList<ContructoFilterChonhuong> arrayFilterChonhuong;
    AdapterFilterChonhuong adapterFilterChonhuong;

    public ModelFilter(ModelReponsetoPresenterFilter callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        dataAddressDictricts = new ArrayList<>();
        arrayFilterChonhuong = new ArrayList<>();
    }

    public void initButtonDataIcon(int requestcode) {
        callback.onButtonDataIcon(requestcode);
    }

    public void initDataChonhuong(final Spinner spin_chonhuong) {
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Chọn hướng --"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Đông"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Tây"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Nam"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Bắc"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Đông Bắc"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Đông Nam"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Tây Bắc"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Tây Nam"));
        adapterFilterChonhuong = new AdapterFilterChonhuong(context, arrayFilterChonhuong);
        spin_chonhuong.setAdapter(adapterFilterChonhuong);
        callback.onDataChonhuong();
    }

    public void initDataDistrictsDropdown(final Spinner spin_District) {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("province_id", "79");
        final Call<InfodataDistrictsDropdown> callback1 = dataClient.InfodataDistrictsDropdown(hashMap);
        callback1.enqueue(new Callback<InfodataDistrictsDropdown>() {
            @Override
            public void onResponse(Call<InfodataDistrictsDropdown> call, Response<InfodataDistrictsDropdown> response) {
                if (response.body() == null) {
                } else {
                    dataAddressDictricts.add(new DataAddressDictrict(0, "Chọn quận --"));
                    dataAddressDictricts.addAll(response.body().getData());
                    arrayAdapter_District = new AdapterPostAddressDictrict(context, dataAddressDictricts);
                    spin_District.setAdapter(arrayAdapter_District);
                    arrayAdapter_District.notifyDataSetChanged();
                    if (dataAddressDictricts.size() > 0) {
//                        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<InfodataDistrictsDropdown> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
                Log.d("District", "Quận, Huyện");

            }
        });
    }

}
