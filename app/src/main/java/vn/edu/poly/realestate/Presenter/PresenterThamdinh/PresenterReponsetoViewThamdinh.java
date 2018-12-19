package vn.edu.poly.realestate.Presenter.PresenterThamdinh;

import vn.edu.poly.realestate.Adapter.AdapterThamdinh;

public interface PresenterReponsetoViewThamdinh {
    void onFetchData(AdapterThamdinh adapter);
    void onIntent();
    void onIntentDataDetails();
}
