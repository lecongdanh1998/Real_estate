package vn.edu.poly.realestate.Presenter.PresenterMain;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;

public interface PresenterReponsetoViewMain {
    void onFecthDataAdapter(ListViewMainActivityAdapter adapter);
    void onIntentData();
}
