package vn.edu.poly.realestate.Model.ModelThamDinh;

import vn.edu.poly.realestate.Adapter.AdapterThamdinh;

public interface ModelReponsetoPresenterThamdinh {
    void onFetchData(AdapterThamdinh adapter);
    void onIntent();
    void onIntentDataDetails();

}
