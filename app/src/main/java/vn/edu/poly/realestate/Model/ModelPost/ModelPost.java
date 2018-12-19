package vn.edu.poly.realestate.Model.ModelPost;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import vn.edu.poly.realestate.Adapter.AdapterPostAddress;
import vn.edu.poly.realestate.Adapter.AdapterPostAddressDictrict;
import vn.edu.poly.realestate.Adapter.AdapterPostAddressWard;
import vn.edu.poly.realestate.Model.PostAddressContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddress;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddressDictrict;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddressWard;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataDistrictsDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataProvincesDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataWardsDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.InsertData.InsertData;
import vn.edu.poly.realestate.Model.RetrofitClient.UpLoadImage.InfodataUploadImage;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.Util.ValidateForm;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.MenuActivity;
import vn.edu.poly.realestate.View.Menu.Menu_nguoidangtinbatdongsan.Dangtinbatdongsan.PostActivity;

public class ModelPost {
    ModelReponsetoPresenterPost callback;
    Context context;
    Activity activity;
    AdapterPostAddress arrayAdapter_City;
    AdapterPostAddressDictrict arrayAdapter_District;
    AdapterPostAddressWard arrayAdapter_Ward;
    DecimalFormat decimalFormat;
    private int PIC_CROP = 3;
    private Uri picUri;
    private int CAMERA_REQUEST = 1;
    private int CAMERA_REQUEST_MAX = 1999;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    ArrayList<DataAddress> dataAddresses;
    ArrayList<DataAddressDictrict> dataAddressDictricts;
    ArrayList<DataAddressWard> dataAddressWards;
//    ArrayList<DataAddress> dataAddresses;

    public ModelPost(ModelReponsetoPresenterPost callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        dataAddresses = new ArrayList<>();
        dataAddressDictricts = new ArrayList<>();
        dataAddressWards = new ArrayList<>();
    }

    public void initIntentView(int requestcode) {
        if (requestcode == 0) {
            intentView(MainActivity.class);
        } else if (requestcode == 1) {

        }
    }

    private void initDataProvincesDropdown(final Spinner spin_city) {
        DataClient dataClient = APIUtils.getData();
        final Call<InfodataProvincesDropdown> callback1 = dataClient.InfodataProvincesDropdown();
        callback1.enqueue(new Callback<InfodataProvincesDropdown>() {
            @Override
            public void onResponse(Call<InfodataProvincesDropdown> call, Response<InfodataProvincesDropdown> response) {
                if (response.body() == null) {
                } else {
                    dataAddresses = response.body().getData();
                    arrayAdapter_City = new AdapterPostAddress(context, dataAddresses);
                    spin_city.setAdapter(arrayAdapter_City);
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

    private void initDataWardDropdown(final Spinner spin_Wards, String id) {
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
                    spin_Wards.setAdapter(arrayAdapter_Ward);
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


    public void initSpinnerAddress(final Spinner spin_Wards, final Spinner spin_District, final Spinner spin_city) {
        initDataProvincesDropdown(spin_city);
        spin_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (dataAddresses.size() > 0) {
                    initDataDistrictsDropdown(spin_District, String.valueOf(dataAddresses.get(position).getId()));
                    callback.onSpinnerAddress(dataAddresses.get(position).getId(), 0);
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
                    initDataWardDropdown(spin_Wards, String.valueOf(dataAddressDictricts.get(position).getId()));
                    callback.onSpinnerAddress(dataAddressDictricts.get(position).getId(), 1);
                } else {
                    Log.d("DataAddresses", "Error");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_Wards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (dataAddressWards.size() > 0) {
                    callback.onSpinnerAddress(dataAddressWards.get(position).getId(), 2);
                } else {
                    Log.d("DataAddresses", "Error");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void initEditTextPrice(EditText edt_price_buy, EditText edt_price_deposit, EditText edt_price_sell, EditText edt_title, EditText edt_address, EditText edt_describe) {
        edt_price_buy.addTextChangedListener(onTextChangedListener(edt_price_buy, 0));
        edt_price_deposit.addTextChangedListener(onTextChangedListener(edt_price_deposit, 1));
        edt_price_sell.addTextChangedListener(onTextChangedListener(edt_price_sell, 2));
        edt_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                callback.onEditTextPrice(context.getResources().getString(R.string.txt_title), 3);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                callback.onEditTextPrice(context.getResources().getString(R.string.txt_address), 4);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_describe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                callback.onEditTextPrice(context.getResources().getString(R.string.txt_description), 5);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                    if (requestcode == 0) {
                        callback.onEditTextPrice(context.getResources().getString(R.string.txt_price_buy), 0);
                    }
                    if (requestcode == 1) {
                        callback.onEditTextPrice(context.getResources().getString(R.string.txt_price_deposit), 1);
                    }
                    if (requestcode == 2) {
                        callback.onEditTextPrice(context.getResources().getString(R.string.txt_price_sell), 2);
                    }
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                editText.addTextChangedListener(this);
            }
        };
    }

    public void initEditTextArea(final EditText edt_user_arearong, final EditText edt_user_areadai) {
        edt_user_arearong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    callback.onEditTextArea("--", 0);
                } else {
                    if (s.toString().equals("0")) {
                        callback.onEditTextArea("--", 0);
                    } else {
                        decimalFormat = new DecimalFormat("###,###,###.#");
                        double arearong = Double.valueOf(s.toString());
                        String areaStrrong = decimalFormat.format(arearong).replace(".", ",");
//                        edt_user_arearong.setText(areaStrrong);
                        if (edt_user_areadai.getText().toString().equals("") || edt_user_areadai.getText().toString().equals("0")) {
                            callback.onEditTextArea("--", 0);
                        } else {
                            double areadai = Double.valueOf(edt_user_areadai.getText().toString());
                            double area = areadai * arearong;
                            String areaStr = decimalFormat.format(area).replace(".", ",");
                            callback.onEditTextArea(areaStr + " m2", 1);

                        }

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_user_areadai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    callback.onEditTextArea("--", 0);
                } else {
                    if (s.toString().equals("0")) {
                        callback.onEditTextArea("--", 0);
                    } else {
                        decimalFormat = new DecimalFormat("###,###,###.#");
                        double areadai = Double.valueOf(s.toString());
                        String areaStrdai = decimalFormat.format(areadai).replace(".", ",");
//                        edt_user_areadai.setText(areaStrdai);
                        if (edt_user_arearong.getText().toString().equals("") || edt_user_arearong.getText().toString().equals("0")) {
                            callback.onEditTextArea("--", 0);
                        } else {
                            double arearong = Double.valueOf(edt_user_arearong.getText().toString());
                            double area = areadai * arearong;
                            String areaStr = decimalFormat.format(area).replace(".", ",");
                            callback.onEditTextArea(areaStr + " m2", 1);
                        }

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void initInsert(final String title, final String address, final String ward, final String district, String city,
                           final String area, final String price_buy, final String price_deposit, final String price_sell, final String description,
                           final String image) {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("title", title);
        hashMap.put("address", address);
        hashMap.put("ward", ward);
        hashMap.put("district", district);
        hashMap.put("city", city);
        hashMap.put("area", area);
        hashMap.put("price_buy", price_buy);
        hashMap.put("price_deposit", price_deposit);
        hashMap.put("price_sell", price_sell);
        hashMap.put("description", description);
        final Call<InsertData> callback1 = dataClient.InsertData(hashMap);
        callback1.enqueue(new Callback<InsertData>() {
            @Override
            public void onResponse(Call<InsertData> call, Response<InsertData> response) {
                if (response.body() == null) {
                    initError(title, address, area, price_buy, price_deposit, price_sell, description, image);
                } else {
                    InsertData.DataUpdate insertData = response.body().getDataUpdate();
                    callback.onFetchInsertData("Thông tin đã được Update, Vui lòng chờ Upload hình ảnh", insertData.getId(), 0);
                }
            }

            @Override
            public void onFailure(Call<InsertData> call, Throwable t) {
                callback.onFetchInsertData("Vui lòng kiểm tra kết nối Internet", "", 0);
            }
        });
    }

    public void initUpLoadImages(final String id, String image) {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("image", "data:image/png;base64," + image);
        final Call<InfodataUploadImage> callback1 = dataClient.InfodataUploadImage(hashMap);
        callback1.enqueue(new Callback<InfodataUploadImage>() {
            @Override
            public void onResponse(Call<InfodataUploadImage> call, Response<InfodataUploadImage> response) {
                if (response.body() == null) {
                    callback.onFetchUpLoad("Hình này bị lỗi");
                } else {
//                    Intent intent = new Intent(activity, PostActivity.class);
//                    activity.startActivity(intent);
//                    activity.overridePendingTransition(R.anim.stay_still, R.anim.stay_still);
//                    activity.finish();
                }
            }

            @Override
            public void onFailure(Call<InfodataUploadImage> call, Throwable t) {
                callback.onFetchInsertData("Vui lòng kiểm tra kết nối Internet", "", 0);
            }
        });
    }

    private void initError(String title,
                           String address,
                           String area,
                           String price_buy,
                           String price_deposit,
                           String price_sell,
                           String description,
                           String image) {
        int errorCode = 0;
        if (new ValidateForm().validateTextEmpty(title)) {
            callback.onFetchInsertData("", context.getResources().getString(R.string.txt_Errortitle), 1);
            errorCode++;
        }
        if (new ValidateForm().validateTextEmpty(address)) {
            callback.onFetchInsertData("", context.getResources().getString(R.string.txt_ErrorAddress), 2);
            errorCode++;
        }
        if (new ValidateForm().validateTextEmpty(price_buy)) {
            callback.onFetchInsertData("", context.getResources().getString(R.string.txt_Errorprice_buy), 3);
            errorCode++;
        }
        if (new ValidateForm().validateTextEmpty(price_deposit)) {
            callback.onFetchInsertData("", context.getResources().getString(R.string.txt_Errorprice_deposit), 4);
            errorCode++;
        }
        if (new ValidateForm().validateTextEmpty(price_sell)) {
            callback.onFetchInsertData("", context.getResources().getString(R.string.txt_Errorprice_sell), 5);
            errorCode++;
        }
        if (new ValidateForm().validateTextEmpty(description)) {
            callback.onFetchInsertData("", context.getResources().getString(R.string.txt_Errordescription), 6);
            errorCode++;
        }
        if (new ValidateForm().validateTextEmpty(image)) {
            callback.onFetchInsertData("", "Vui lòng chọn hình ảnh!", 7);
            errorCode++;
        }
        if (new ValidateForm().validateTextEmpty(area)) {
            callback.onFetchInsertData("", context.getResources().getString(R.string.txt_Errorarea), 8);
            errorCode++;
        }
    }


    public void SDK_Camera() {
        if (Build.VERSION.SDK_INT > 21) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                    callback.onFetchCamera(takePictureIntent, CAMERA_REQUEST_MAX);
                }
            } else {
                // Permission is missing and must be requested.
                requestCameraPermission();
            }
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            callback.onFetchCamera(takePictureIntent, CAMERA_REQUEST);

        }
    }

    private void requestCameraPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
//            // Display a SnackBar with a button to request the missing permission.
            Toast.makeText(activity, "Camera access is required to display the camera preview.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(activity, "Permission is not available. Requesting camera permission.", Toast.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        }
    }

    public void galleryUpload() {
        Intent imageDownload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imageDownload.putExtra("crop", "true");
        imageDownload.putExtra("aspectX", 0);
        imageDownload.putExtra("aspectY", 0);
        imageDownload.putExtra("outputX", 200);
        imageDownload.putExtra("outputY", 150);
        imageDownload.putExtra("return-data", true);
        callback.onFetchGallery(imageDownload);
    }


    private void intentView(Class c) {
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
        activity.finish();
    }
}
