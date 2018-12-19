package vn.edu.poly.realestate.Model.ModelDetailsThamdinh;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.AdapterDetailsThamdinhUserRating;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.DuyetTinDang.DuyetTinDang;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataDetails.InfodataDetails;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingData;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingEstimateContructor.RatingEstimateContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.UserInfo.UserInfo;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.Menu.MenuActivity;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.Details.DetailsChoThamDinhActivity;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.ThamDinhActivity;
import vn.edu.poly.realestate.View.User.SignInActivity;

public class ModelDetailsThamdinh {
    ModelReponsetoPresenterThamdinh callback;
    Context context;
    float rating1 = 0;
    int Sell;
    String access_token;
    long Deposit, DepositBuy;
    Activity activity;
    int BuyNull, SellNull = 0;
    DecimalFormat decimalFormat;
    Dialog dialog;
    String idUser;
    String id;
    AdapterDetailsThamdinhUserRating adapterDetailsThamdinhUserRating;

    public ModelDetailsThamdinh(ModelReponsetoPresenterThamdinh callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }


    public void initFetchDataId(final String getId) {
        id = BaseActivity.dataLoginInfo.getString("idDetailsThamdinh", "");
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        final Call<RatingContructor> callback1 = dataClient.RatingContructor(hashMap);
        callback1.enqueue(new Callback<RatingContructor>() {
            @Override
            public void onResponse(Call<RatingContructor> call, Response<RatingContructor> response) {
                ArrayList<RatingData> ratingData = response.body().getData();
                if (response.body() == null) {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                } else {
                    if (ratingData.size() > 0) {
                        float tong = 0;
                        int priceBuy, priceBuy1 = 0;
                        int priceSell, priceSell1 = 0;
                        for (int i = 0; i < ratingData.size(); i++) {
                            tong = tong + Float.parseFloat(ratingData.get(i).getRating());
                            if (ratingData.get(i).getPriceBuy() != null) {
                                BuyNull++;
                                double se = Double.valueOf(ratingData.get(i).getPriceBuy());
                                priceBuy = (int) se;
                                priceBuy1 = priceBuy1 + priceBuy;
                            }
                            if (ratingData.get(i).getPriceDeposit() != null) {
                                SellNull++;
                                double se = Double.valueOf(ratingData.get(i).getPriceDeposit());
                                priceSell = (int) se;
                                priceSell1 = priceSell1 + priceSell;
                            }


                            if (!ratingData.get(i).getUser_id().toString().equals(getId)) {
                                callback.onButton(1);
                            } else if (ratingData.get(i).getUser_id().toString().equals(getId)) {
                                callback.onButton(0);
                            }
                        }
                        adapterDetailsThamdinhUserRating = new AdapterDetailsThamdinhUserRating(context, response.body().getData());
                        callback.onFetchDataId(adapterDetailsThamdinhUserRating,
                                tong / ratingData.size(),
                                priceBuy1 / BuyNull,
                                priceSell1 / SellNull);
                        BuyNull = 0;
                        SellNull = 0;
                        rating1 = 0;
                    } else {
                        adapterDetailsThamdinhUserRating = new AdapterDetailsThamdinhUserRating(context, response.body().getData());
                        callback.onFetchDataId(adapterDetailsThamdinhUserRating,
                                0,
                                0,
                                0);
                    }

                }
            }

            @Override
            public void onFailure(Call<RatingContructor> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet Rating", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void initFetchData() {
        id = BaseActivity.dataLoginInfo.getString("idDetailsThamdinh", "");
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        final Call<InfodataDetails> callback1 = dataClient.Infodatadetails(hashMap);
        callback1.enqueue(new Callback<InfodataDetails>() {
            @Override
            public void onResponse(Call<InfodataDetails> call, Response<InfodataDetails> response) {
                if (response.body() == null) {
                    callback.onFetchDataId("Lỗi không load được", 0);
                } else {
                    Data data = response.body().getData();
                    callback.onFetchDataId(data.getTitle(), 6);
                    callback.onFetchDataId(data.getImage(), 1);
                    callback.onFetchDataId(data.getAddress(), 2);
                    handleFetchData(data.getPrice_sell(), data.getPrice_deposit(), data.getPrice_buy());

                }

            }

            @Override
            public void onFailure(Call<InfodataDetails> call, Throwable t) {
                callback.onFetchDataId("Vui lòng kiểm tra kết nối Internet", 0);
            }
        });

    }


    public void initDialogDataDuyet() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                context);
        alertDialog2.setTitle(context.getResources().getString(R.string.txt_duyet));
        alertDialog2.setMessage("Bạn có chắc duyệt bài viết này?");
        alertDialog2.setIcon(R.drawable.ic_mode_edit_black_24dp);
        alertDialog2.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        initOnButtonDuyet();
                    }
                });
        alertDialog2.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
    }


    public void initDialogData(final float rating1, final String extBuy, final String extSell) {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                context);
        alertDialog2.setTitle(context.getResources().getString(R.string.txt_danhgia));
        alertDialog2.setMessage("Bạn đánh giá bài viết này " + rating1 + " sao ?");
        alertDialog2.setIcon(R.drawable.ic_star_border_yellow_600_24dp);
        alertDialog2.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        initFetchDataThamdinh(rating1, extBuy, extSell);
                    }
                });
        alertDialog2.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
    }

    public void initOnButtonDuyet() {
        id = BaseActivity.dataLoginInfo.getString("idDetailsThamdinh", "");
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        final Call<DuyetTinDang> callback1 = dataClient.DuyetTinDang(hashMap);
        callback1.enqueue(new Callback<DuyetTinDang>() {
            @Override
            public void onResponse(Call<DuyetTinDang> call, Response<DuyetTinDang> response) {
                if (response.body() == null) {
                    callback.onButtonDuyetTinDang("Lỗi");
                } else {
                    callback.onButtonDuyetTinDang("Đã Duyệt");
                    intentView(ThamDinhActivity.class, 0);
                }

            }

            @Override
            public void onFailure(Call<DuyetTinDang> call, Throwable t) {
                callback.onButtonDuyetTinDang("Vui lòng kiểm tra kết nối Internet");
            }
        });
    }


    public void initInfoDataUser() {
        access_token = BaseActivity.dataLoginUser.getString("access_token", "");
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Authorization", "Bearer " + access_token);
        Call<UserInfo> callback1 = dataClient.UserInfo(hashMap);
        callback1.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo.Data userInfo = response.body().getData();
                if (response.body() == null) {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                } else {
                    idUser = String.valueOf(userInfo.getId());
                    initFetchDataId(String.valueOf(userInfo.getId()));
                    if (userInfo.getRole().equals("admin")) {
                        callback.onButton(2);
                    }
                    if (userInfo.getRole().equals("appraisers")) {
                        callback.onButton(3);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet Info", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initFetchDataThamdinh(float rating1, String extBuy, String extSell) {
        id = BaseActivity.dataLoginInfo.getString("idDetailsThamdinh", "");
        DataClient dataClient = APIUtils.getData();
        String strBuy = extBuy.toString().replace(",", "");
        String strSell = extSell.toString().replace(",", "");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("rsinfo_id", id);
        hashMap.put("rating", String.valueOf(rating1));
        hashMap.put("comment", "");
        hashMap.put("price_buy", strBuy);
        hashMap.put("price_deposit", strSell);
        hashMap.put("user_id", idUser);
        final Call<RatingEstimateContructor> callback1 = dataClient.RatingEstimateContructor(hashMap);
        callback1.enqueue(new Callback<RatingEstimateContructor>() {
            @Override
            public void onResponse(Call<RatingEstimateContructor> call, Response<RatingEstimateContructor> response) {
                if (response.body() == null) {
                    callback.onFetchDataThamdinh("Lỗi", 0);
                } else {
                    callback.onFetchDataThamdinh("Thành công", 1);
                    initInfoDataUser();
                }
            }

            @Override
            public void onFailure(Call<RatingEstimateContructor> call, Throwable t) {
                callback.onFetchDataThamdinh("Vui lòng kiểm tra kết nối Internet", 0);
            }
        });
    }


    public void handleFetchData(String Strban, String Strtiencoc, String Strtienmua) {
        double se = Double.valueOf(Strtienmua);
        Sell = (int) se;
        decimalFormat = new DecimalFormat("###,###,###.#");
        double selldouble = Double.valueOf(Sell);
        String sellStr = decimalFormat.format(selldouble).replace(".", ",");
        callback.onFetchDataId(sellStr + " Xu", 3);

        //Deposit
        Deposit = (long) (Double.parseDouble(Strtiencoc));
        String DepositStr = decimalFormat.format(Deposit).replace(".", ",");
        double Depositphantramdouble = Double.valueOf(Double.parseDouble(Strtiencoc) * 100) / (Double.parseDouble(Strtienmua));
        String DepositphantramStr = decimalFormat.format(Depositphantramdouble).replace(".", ",");
        callback.onFetchDataId(DepositStr + " (" + DepositphantramStr + "%)" + " Xu",
                4);

        //DepositBuy
        DepositBuy = (long) (Double.parseDouble(Strban));
        String DepositBuyStr = decimalFormat.format(DepositBuy).replace(".", ",");
        double DepositBuyphantramdouble = Double.valueOf(Double.parseDouble(Strban) * 100);
        String DepositBuyphantramStr = decimalFormat.format(DepositBuyphantramdouble).replace("" +
                ".", ",");
        callback.onFetchDataId(DepositBuyStr + " Xu", 5);

    }


    public void dialogSubmit() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thamdinh);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RatingBar rb_rating_dialog = dialog.findViewById(R.id.rb_rating_dialog);
        final EditText edt_price_buy = dialog.findViewById(R.id.edt_price_buy);
        final EditText edt_price_sell = dialog.findViewById(R.id.edt_price_sell);
        Button btn_cancel_dialog = dialog.findViewById(R.id.btn_cancel_dialog);
        Button btn_danhgia_dialog = dialog.findViewById(R.id.btn_danhgia_dialog);
        initEditTextPrice(edt_price_buy, edt_price_sell);
        rb_rating_dialog.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating1 = rating;
            }
        });

        btn_danhgia_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialogData(rating1, String.valueOf(edt_price_buy.getText().toString()), String.valueOf(edt_price_sell.getText().toString()));
                dialog.cancel();
            }
        });
        btn_cancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
        callback.onDialogSubmit();
    }


    public void initIntent(int requestcode) {
        if (requestcode == 0) {
            intentView(ThamDinhActivity.class, 0);
        } else if (requestcode == 1) {
//            intentView(DetailsChoThamDinhActivity.class, 1);
        }
        callback.onIntent();
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

    private void initEditTextPrice(EditText edt_price_buy, EditText edt_price_sell) {
        edt_price_buy.addTextChangedListener(onTextChangedListener(edt_price_buy));
        edt_price_sell.addTextChangedListener(onTextChangedListener(edt_price_sell));

    }

    private TextWatcher onTextChangedListener(final EditText editText) {
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
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                editText.addTextChangedListener(this);
            }
        };
    }

}
