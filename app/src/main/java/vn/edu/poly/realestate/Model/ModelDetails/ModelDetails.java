package vn.edu.poly.realestate.Model.ModelDetails;

import android.content.Context;

import java.text.DecimalFormat;

import vn.edu.poly.realestate.Model.ModelUser.ModelSignIn.ModelReponseToPresenterSignIn;
import vn.edu.poly.realestate.R;

public class ModelDetails {
    ModelReponsetoPresenterDetails callback;
    Context context;
    int Sell;
    long Deposit, DepositBuy;
    public static int requestCodeTVdangduoccoc = 0;
    public static int requestCodeTVtiencoc = 1;
    public static int requestCodeTVtienmua = 2;
    public static int requestCodeTVdautu = 3;
    public static int requestCodeTVphantram = 4;
    public static int requestCodeTVdaututoida = 5;
    DecimalFormat decimalFormat,decimalFormatphantram;

    public ModelDetails(ModelReponsetoPresenterDetails callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void handleFetchData(String Strdangduoccoc, String Strtiencoc, String Strtienmua) {
        Sell = Integer.parseInt(Strtienmua);
        decimalFormat = new DecimalFormat("###,###,###.#");
        double selldouble = Double.valueOf(Strtienmua);
        String sellStr = decimalFormat.format(selldouble).replace(".", ",");
        //Sell
        callback.onDetailsFetchDataSuccess(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + sellStr + " Xu", requestCodeTVtienmua);
        //Deposit
        Deposit = (long) (Sell * Double.parseDouble(Strdangduoccoc));
        String DepositStr = decimalFormat.format(Deposit).replace(".", ",");
        double Depositphantramdouble = Double.valueOf(Double.parseDouble(Strdangduoccoc) * 100);
        String DepositphantramStr = decimalFormat.format(Depositphantramdouble).replace(".", ",");
        callback.onDetailsFetchDataSuccess(context.getResources().getString(R.string.txt_tiencoc) + " : " + DepositStr + " Xu" + " (" + DepositphantramStr + "%)", requestCodeTVdangduoccoc);
        //DepositBuy
        DepositBuy = (long) (Sell * (Double.parseDouble(Strtiencoc)));
        String DepositBuyStr = decimalFormat.format(DepositBuy).replace(".", ",");
        double DepositBuyphantramdouble = Double.valueOf(Double.parseDouble(Strtiencoc) * 100);
        String DepositBuyphantramStr = decimalFormat.format(DepositBuyphantramdouble).replace(".", ",");
        callback.onDetailsFetchDataSuccess(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + DepositBuyStr + " Xu" + " (" + DepositBuyphantramStr + "%)", requestCodeTVtiencoc);
        double phantramdautu = 100 - DepositBuyphantramdouble;
        String phantramdautuStr = decimalFormat.format(phantramdautu).replace(".", ",");
        long phantramdautuLong = (long) ((phantramdautu * DepositBuy)/DepositBuyphantramdouble);
        String phantramdautuLongStr = decimalFormat.format(phantramdautuLong).replace(".", ",");
        callback.onDetailsFetchDataSuccess(context.getResources().getString(R.string.txt_sotiendautuconlai) + " : " + phantramdautuLongStr + " Xu" + " (" + phantramdautuStr + "%)", requestCodeTVdautu);
        callback.onDetailsFetchDataSuccess(phantramdautuLongStr, requestCodeTVphantram);

    }
}
