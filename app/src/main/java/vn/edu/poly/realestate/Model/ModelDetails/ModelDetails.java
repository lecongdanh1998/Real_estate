package vn.edu.poly.realestate.Model.ModelDetails;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import java.text.DecimalFormat;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ModelUser.ModelSignIn.ModelReponseToPresenterSignIn;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.User.SignInActivity;

public class ModelDetails {
    ModelReponsetoPresenterDetails callback;
    Context context;
    int Sell;
    long Deposit, DepositBuy;
    Activity activity;
    public static int requestCodeTVdangduoccoc = 0;
    public static int requestCodeTVtiencoc = 1;
    public static int requestCodeTVtienmua = 2;
    public static int requestCodeTVdautu = 3;
    public static int requestCodeTVphantram = 4;
    public static int requestCodeTVdaututoida = 5;
    DecimalFormat decimalFormat;

    public ModelDetails(ModelReponsetoPresenterDetails callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }

    public void handleFetchData(String Strdangduoccoc, String Strtiencoc, String Strtienmua) {
        Sell = Integer.parseInt(Strtienmua);
        decimalFormat = new DecimalFormat("###,###,###.#");
        double selldouble = Double.valueOf(Strtienmua);
        String sellStr = decimalFormat.format(selldouble).replace(".", ",");
        callback.onDetailsFetchDataSuccess(sellStr + " Xu", requestCodeTVtienmua);

        //Deposit
        Deposit = (long) (Sell * Double.parseDouble(Strdangduoccoc));
        String DepositStr = decimalFormat.format(Deposit).replace(".", ",");
        double Depositphantramdouble = Double.valueOf(Double.parseDouble(Strdangduoccoc) * 100);
        String DepositphantramStr = decimalFormat.format(Depositphantramdouble).replace(".", ",");
        callback.onDetailsFetchDataSuccess(DepositStr + " Xu" + " (" + DepositphantramStr + "%)",
                requestCodeTVdangduoccoc);

        //DepositBuy
        DepositBuy = (long) (Sell * (Double.parseDouble(Strtiencoc)));
        String DepositBuyStr = decimalFormat.format(DepositBuy).replace(".", ",");
        double DepositBuyphantramdouble = Double.valueOf(Double.parseDouble(Strtiencoc) * 100);
        String DepositBuyphantramStr = decimalFormat.format(DepositBuyphantramdouble).replace("" +
                ".", ",");
        callback.onDetailsFetchDataSuccess(DepositBuyStr + " Xu" + " (" + DepositBuyphantramStr +
                "%)", requestCodeTVtiencoc);

        double phantramdautu = 100 - DepositBuyphantramdouble;
        String phantramdautuStr = decimalFormat.format(phantramdautu).replace(".", ",");
        long phantramdautuLong = (long) ((phantramdautu * DepositBuy) / DepositBuyphantramdouble);
        String phantramdautuLongStr = decimalFormat.format(phantramdautuLong).replace(".", ",");
        callback.onDetailsFetchDataSuccess("Đầu tư tối đa: " + phantramdautuLongStr + " Xu" + " (" + phantramdautuStr
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


}
