package vn.edu.poly.realestate.Presenter.PresenterMain;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.Model.ModelMain.ModelMain;
import vn.edu.poly.realestate.Model.ModelMain.ModelReponsetoPresenterMain;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;

public class PresenterMain implements ModelReponsetoPresenterMain {

    private ModelMain modelMain;
    PresenterReponsetoViewMain callback;
    Context context;
    Activity activity;

    public PresenterMain(PresenterReponsetoViewMain callback, Context context,Activity activity) {
        this.callback = callback;
        this.context = context;
        this.activity = activity;
        modelMain = new ModelMain(this,context,activity);
    }

    public void ReceivedHanleData(){
        modelMain.handleFetchData();
    }
    public void IntentData(ListViewMainActivityContructor contructor,int postion){
        modelMain.IntentData(contructor,postion);
    }
    public void Exit(int position){
        modelMain.onExit(position);
    }

    public void FetchLogout(){
        modelMain.FetchLogout();
    }
    public void ShowDialogHelp(){
        modelMain.ShowDialogHelp();
    }





    @Override
    public void onFecthDataAdapter(ListViewMainActivityAdapter adapter) {
        callback.onFecthDataAdapter(adapter);
    }

    @Override
    public void onIntentData() {
        callback.onIntentData();
    }


    @Override
    public void onFetchLogout() {
        callback.onFetchLogout();

    }

    @Override
    public void onExit() {
        callback.onExit();
    }

    @Override
    public void onShowDialogHelp() {
        callback.onShowDialogHelp();
    }

}
