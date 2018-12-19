package vn.edu.poly.realestate.Model.ModelDetails;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ModelUser.ModelSignIn.ModelReponseToPresenterSignIn;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataDetails.InfodataDetails;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Infodatadeposit;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.User.SignInActivity;

public class ModelDetails {
    ModelReponsetoPresenterDetails callback;
    Context context;
    int Sell;
    long Deposit, DepositBuy;
    Activity activity;
    String id;
    public static int requestCodeTVdangduoccoc = 0;
    public static int requestCodeTVtiencoc = 1;
    public static int requestCodeTVtienmua = 2;
    public static int requestCodeTVdautu = 3;
    public static int requestCodeTVphantram = 4;
    public static int requestCodeTVdaututoida = 5;
    public static int requestCodeAddress = 6;
    DecimalFormat decimalFormat;
    Dialog dialog;

    public ModelDetails(ModelReponsetoPresenterDetails callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }

    public void handleFetchData(String Strban, String Strtiencoc, String Strtienmua) {
        double se = Double.valueOf(Strtienmua);
        Sell = (int) se;
        decimalFormat = new DecimalFormat("###,###,###.#");
        double selldouble = Double.valueOf(Sell);
        String sellStr = decimalFormat.format(selldouble).replace(".", ",");
        callback.onDetailsFetchDataSuccess(sellStr, requestCodeTVtienmua);

        //Deposit
        Deposit = (long) (Double.parseDouble(Strtiencoc));
        String DepositStr = decimalFormat.format(Deposit).replace(".", ",");
        double Depositphantramdouble = Double.valueOf(Double.parseDouble(Strtiencoc) * 100) / (Double.parseDouble(Strtienmua));
        String DepositphantramStr = decimalFormat.format(Depositphantramdouble).replace(".", ",");
        callback.onDetailsFetchDataSuccess(DepositStr + " (" + DepositphantramStr + "%)",
                requestCodeTVdangduoccoc);

        //DepositBuy
        DepositBuy = (long) (Double.parseDouble(Strban));
        String DepositBuyStr = decimalFormat.format(DepositBuy).replace(".", ",");
        double DepositBuyphantramdouble = Double.valueOf(Double.parseDouble(Strban) * 100);
        String DepositBuyphantramStr = decimalFormat.format(DepositBuyphantramdouble).replace("" +
                ".", ",");
        callback.onDetailsFetchDataSuccess(DepositBuyStr, requestCodeTVtiencoc);

        double phantramdautu = 100 - Depositphantramdouble;
        String phantramdautuStr = decimalFormat.format(phantramdautu).replace(".", ",");
        long phantramdautuLong = (long) ((phantramdautu * selldouble) / 100);
        String phantramdautuLongStr = decimalFormat.format(phantramdautuLong).replace(".", ",");
        callback.onDetailsFetchDataSuccess("Đầu tư tối đa: " + phantramdautuLongStr + " (" + phantramdautuStr
                + "%)", requestCodeTVdautu);
        callback.onDetailsFetchDataSuccess(String.valueOf(phantramdautuLong),
                requestCodeTVphantram);
    }

    public void onBack(int requestcode) {
        if (requestcode == 0) {
            intentView(MainActivity.class);
        }
        if (requestcode == 1) {
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    context);
            alertDialog2.setTitle("Submit...");
            alertDialog2.setMessage("Bạn có chắc chắn muốn đầu tư không?");
            alertDialog2.setIcon(R.drawable.coins);
            alertDialog2.setPositiveButton("Có",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            intentView(MainActivity.class);
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
        callback.onBack();

    }

    private void intentView(Class c) {
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
        activity.finish();
    }

    public void ShowDialogHelp() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_question);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btn_ok_dialogquestion = dialog.findViewById(R.id.btn_ok_dialogquestion);
        btn_ok_dialogquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        callback.onShowDialogHelp();
    }

    public void initFetchDataId() {
        id = BaseActivity.dataLoginInfo.getString("id", "");
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
                    handleFetchData(data.getPrice_sell(), data.getPrice_deposit(), data.getPrice_buy());
                    callback.onDetailsFetchDataSuccess(data.getAddress(),
                            requestCodeAddress);
                    callback.onFetchDataId(data.getImage(), 1);
                }

            }

            @Override
            public void onFailure(Call<InfodataDetails> call, Throwable t) {
                callback.onFetchDataId("Vui lòng kiểm tra kết nối Internet", 0);
            }
        });

    }


}
