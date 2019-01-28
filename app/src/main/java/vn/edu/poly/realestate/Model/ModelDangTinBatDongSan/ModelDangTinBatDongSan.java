package vn.edu.poly.realestate.Model.ModelDangTinBatDongSan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.AdapterFilterChonhuong;
import vn.edu.poly.realestate.Adapter.AdapterFilterGiayChuQuyen;
import vn.edu.poly.realestate.Adapter.AdapterFilterLoaiDat;
import vn.edu.poly.realestate.Adapter.AdapterFilterPhong;
import vn.edu.poly.realestate.Adapter.AdapterPostAddress;
import vn.edu.poly.realestate.Adapter.AdapterPostAddressDictrict;
import vn.edu.poly.realestate.Adapter.AdapterPostAddressWard;
import vn.edu.poly.realestate.Model.ModelFilter.ContructoFilterChonhuong;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddress;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddressDictrict;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddressWard;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataDistrictsDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataProvincesDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataWardsDropdown;
import vn.edu.poly.realestate.R;

public class ModelDangTinBatDongSan {
    ModelReponsetoPresenterDangTinBatDongSan callback;
    Context context;
    Activity activity;
    ArrayList<DataAddress> dataAddresses;
    ArrayList<DataAddressDictrict> dataAddressDictricts;
    ArrayList<DataAddressWard> dataAddressWards;
    AdapterPostAddress arrayAdapter_City;
    AdapterPostAddressDictrict arrayAdapter_District;
    AdapterPostAddressWard arrayAdapter_Ward;
    ArrayList<ContructoFilterChonhuong> arrayFilterChonhuong;
    AdapterFilterChonhuong adapterFilterChonhuong;
    int numberWard = 0;
    int numberDistrict = 0;
    int numberCity = 0;
    AdapterFilterLoaiDat adapterFilterLoaiDat;
    AdapterFilterPhong adapterFilterPhong;
    ArrayList<ContructoFilterLoaiDat> arrayListLoaiDat;
    ArrayList<ContructoFiltePhong> arrayListPhong;
    ArrayList<ContructoFilteGiayChuQuyen> arrayListGiayChuQuyen;
    AdapterFilterGiayChuQuyen adapterFilterGiayChuQuyen;
    String txt_address;
    View rootView;
    int keyboardHeight,numBerOne;
    public ModelDangTinBatDongSan(ModelReponsetoPresenterDangTinBatDongSan callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        dataAddresses = new ArrayList<>();
        dataAddressDictricts = new ArrayList<>();
        dataAddressWards = new ArrayList<>();
        arrayFilterChonhuong = new ArrayList<>();
        arrayListLoaiDat = new ArrayList<>();
        arrayListPhong = new ArrayList<>();
        arrayListGiayChuQuyen = new ArrayList<>();
    }

    public void initEditSearch(final String search) {
        callback.onEditTextSearch(search);

    }

    public void initDataChonhuong() {
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Đông"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Tây"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Nam"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Bắc"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Đông Bắc"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Đông Nam"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Tây Bắc"));
        arrayFilterChonhuong.add(new ContructoFilterChonhuong("Tây Nam"));
        adapterFilterChonhuong = new AdapterFilterChonhuong(context, arrayFilterChonhuong);
        callback.onDataChonhuong(adapterFilterChonhuong);
    }

    public void initEditTextPrice(EditText edt_gia, EditText edt_giathuongluong, EditText edt_giacoc) {
        edt_gia.addTextChangedListener(onTextChangedListener(edt_gia, 0));
        edt_giathuongluong.addTextChangedListener(onTextChangedListener(edt_giathuongluong, 1));
        edt_giacoc.addTextChangedListener(onTextChangedListener(edt_giacoc, 2));
    }

    public void initFindDataAddress(final String edt_find, String spinnerCity, String spinnerDictric, String spinnerWard) {
        if (!edt_find.toString().equals("")) {
            txt_address = edt_find.toString()
                    + ", " + spinnerWard
                    + ", " + spinnerDictric
                    + ", " + spinnerCity;
            callback.onFetchAddress(txt_address);
        }
    }

    public void initOnKeyBoard(final CardView cardView_button_find){
        rootView = activity.getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = rootView.getHeight();
                keyboardHeight = screenHeight - (rect.bottom - rect.top);
                if(keyboardHeight > screenHeight / 3){
                    if(numBerOne == 1) {
                        cardView_button_find.setVisibility(View.GONE);
                        numBerOne = 0;
                    }
                } else{
                    if(numBerOne == 0) {
                        cardView_button_find.setVisibility(View.VISIBLE);
                        numBerOne = 1;
                    }
                }
            }
        });
        callback.onKeyboard();
    }


    private TextWatcher onTextChangedListener(final EditText editText, final int requestcode) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editText.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    editText.setText(formattedString);
                    editText.setSelection(editText.getText().length());
//                    if (requestcode == 0) {
//                        callback.onEditTextPrice(context.getResources().getString(R.string.txt_price_buy), 0);
//                    }
//                    if (requestcode == 1) {
//                        callback.onEditTextPrice(context.getResources().getString(R.string.txt_price_deposit), 1);
//                    }
//                    if (requestcode == 2) {
//                        callback.onEditTextPrice(context.getResources().getString(R.string.txt_price_sell), 2);
//                    }
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                editText.addTextChangedListener(this);
            }
        };
    }


    public void initDataPhong() {
        arrayListPhong.clear();
        arrayListPhong.add(new ContructoFiltePhong("1 phòng"));
        arrayListPhong.add(new ContructoFiltePhong("2 phòng"));
        arrayListPhong.add(new ContructoFiltePhong("3 phòng"));
        arrayListPhong.add(new ContructoFiltePhong("4 phòng"));
        arrayListPhong.add(new ContructoFiltePhong("5 phòng"));
        adapterFilterPhong = new AdapterFilterPhong(context, arrayListPhong);
        callback.onDataPhong(adapterFilterPhong);
    }

    public void initDataGiayChuQuyen() {
        arrayListGiayChuQuyen.add(new ContructoFilteGiayChuQuyen("Sổ đỏ"));
        arrayListGiayChuQuyen.add(new ContructoFilteGiayChuQuyen("Sổ hồng"));
        adapterFilterGiayChuQuyen = new AdapterFilterGiayChuQuyen(context, arrayListGiayChuQuyen);
        callback.onDataGiayChuQuyen(adapterFilterGiayChuQuyen);
    }

    public void initDataLoaiDat() {
        arrayListLoaiDat.add(new ContructoFilterLoaiDat("Nhà riêng"));
        arrayListLoaiDat.add(new ContructoFilterLoaiDat("Đất nền"));
        arrayListLoaiDat.add(new ContructoFilterLoaiDat("Chung cư/căn hộ"));
        arrayListLoaiDat.add(new ContructoFilterLoaiDat("Đất nền dự án"));
        adapterFilterLoaiDat = new AdapterFilterLoaiDat(context, arrayListLoaiDat);
        callback.onDataLoaiDat(adapterFilterLoaiDat);
    }


    private void initDataProvincesDropdown(final Spinner spinnerCity) {
        DataClient dataClient = APIUtils.getData();
        final Call<InfodataProvincesDropdown> callback1 = dataClient.InfodataProvincesDropdown();
        callback1.enqueue(new Callback<InfodataProvincesDropdown>() {
            @Override
            public void onResponse(Call<InfodataProvincesDropdown> call, Response<InfodataProvincesDropdown> response) {
                if (response.body() == null) {
                } else {
                    dataAddresses = response.body().getData();
                    arrayAdapter_City = new AdapterPostAddress(context, dataAddresses);
                    spinnerCity.setAdapter(arrayAdapter_City);
                    arrayAdapter_City.notifyDataSetChanged();
                    if (dataAddresses.size() > 0) {
//                        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<InfodataProvincesDropdown> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
                Log.d("City", "Thành phố");

            }
        });
    }


    private void initDataDistrictsDropdown(final Spinner spin_District, String id) {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("province_id", id);
        final Call<InfodataDistrictsDropdown> callback1 = dataClient.InfodataDistrictsDropdown(hashMap);
        callback1.enqueue(new Callback<InfodataDistrictsDropdown>() {
            @Override
            public void onResponse(Call<InfodataDistrictsDropdown> call, Response<InfodataDistrictsDropdown> response) {
                if (response.body() == null) {
                } else {
                    dataAddressDictricts = response.body().getData();
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

    private void initDataWardDropdown(final Spinner spinnerWard, String id) {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("district_id", id);
        final Call<InfodataWardsDropdown> callback1 = dataClient.InfodataWardsDropdown(hashMap);
        callback1.enqueue(new Callback<InfodataWardsDropdown>() {
            @Override
            public void onResponse(Call<InfodataWardsDropdown> call, Response<InfodataWardsDropdown> response) {
                if (response.body() == null) {
                } else {
                    dataAddressWards = response.body().getData();
                    arrayAdapter_Ward = new AdapterPostAddressWard(context, dataAddressWards);
                    spinnerWard.setAdapter(arrayAdapter_Ward);
                    arrayAdapter_Ward.notifyDataSetChanged();
                    if (dataAddressWards.size() > 0) {
//                        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<InfodataWardsDropdown> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
                Log.d("Ward", "Phường, Xã");

            }
        });
    }


    public void initSpinnerAddress(final Spinner spinnerWard, final Spinner spin_District, final Spinner spinnerCity) {
        initDataProvincesDropdown(spinnerCity);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (dataAddresses.size() > 0) {
                    initDataDistrictsDropdown(spin_District, String.valueOf(dataAddresses.get(position).getId()));
                    callback.onSpinnerAddress(dataAddresses.get(position).getTitle(), 0);
                } else {
                    Log.d("DataAddresses", "Error");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_District.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (dataAddressDictricts.size() > 0) {
                    initDataWardDropdown(spinnerWard, String.valueOf(dataAddressDictricts.get(position).getId()));
                    callback.onSpinnerAddress(dataAddressDictricts.get(position).getTitle(), 1);
                } else {
                    Log.d("DataAddresses", "Error");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (dataAddressWards.size() > 0) {
                    callback.onSpinnerAddress(dataAddressWards.get(position).getTitle(), 2);
                } else {
                    Log.d("DataAddresses", "Error");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
