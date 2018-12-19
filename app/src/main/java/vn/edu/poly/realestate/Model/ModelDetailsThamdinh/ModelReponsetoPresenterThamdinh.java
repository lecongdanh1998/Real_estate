package vn.edu.poly.realestate.Model.ModelDetailsThamdinh;

import vn.edu.poly.realestate.Adapter.AdapterDetailsThamdinhUserRating;

public interface ModelReponsetoPresenterThamdinh {
    void onIntent();
    void onDialogSubmit();
    void onFetchDataId(String str, int requestcode);
    void onFetchDataThamdinh(String str, int requestcode);
    void onFetchDataId(AdapterDetailsThamdinhUserRating adapterDetailsThamdinhUserRating,
                       float rating, int priceBuy,int priceSell);
    void onButton(int requestcode);
    void onButtonDuyetTinDang(String str);
}
