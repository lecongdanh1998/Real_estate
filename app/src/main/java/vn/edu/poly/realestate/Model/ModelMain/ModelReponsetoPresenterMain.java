package vn.edu.poly.realestate.Model.ModelMain;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;

public interface ModelReponsetoPresenterMain {
    void onFecthDataAdapter(ListViewMainActivityAdapter adapter);
    void onIntentData();

}
