package vn.edu.poly.realestate.Model.ModelMain;

import android.view.View;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;

public interface ModelReponsetoPresenterMain {
    void onFecthDataAdapter(ListViewMainActivityAdapter adapter);
    void onIntentData();
    void onFetchLogout();
    void onExit();
    void onShowDialogHelp();
    void onButtonIntent();
    void onAddress(String address);
}
