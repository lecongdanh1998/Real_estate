package vn.edu.poly.realestate.Presenter.PresenterMain;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.Model.ModelMain.ModelMain;
import vn.edu.poly.realestate.Model.ModelMain.ModelReponsetoPresenterMain;

public class PresenterMain implements ModelReponsetoPresenterMain {
    private ModelMain modelMain;
    PresenterReponsetoViewMain callback;
    Context context;
    public PresenterMain(PresenterReponsetoViewMain callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void ReceivedHanleData(){
        modelMain = new ModelMain(this,context);
        modelMain.handleFetchData();
    }
    public void IntentData(ListViewMainActivityContructor contructor){
        modelMain = new ModelMain(this,context);
        modelMain.IntentData(contructor);
    }

    @Override
    public void onFecthDataAdapter(ListViewMainActivityAdapter adapter) {
        callback.onFecthDataAdapter(adapter);
    }

    @Override
    public void onIntentData() {
        callback.onIntentData();
    }
}
