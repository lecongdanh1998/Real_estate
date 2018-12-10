package vn.edu.poly.realestate.Presenter.PresenterMain;

import android.view.View;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;

public interface PresenterReponsetoViewMain {
    void onFecthDataAdapter(ListViewMainActivityAdapter adapter);
    void onIntentData();
    void onFetchLogout();
    void onExit();
    void onShowDialogHelp();
}
