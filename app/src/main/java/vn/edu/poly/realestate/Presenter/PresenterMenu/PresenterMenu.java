package vn.edu.poly.realestate.Presenter.PresenterMenu;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TableRow;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ModelMenu.ModelMenu;
import vn.edu.poly.realestate.Model.ModelMenu.ModelReponsetoModelMenu;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.HistoryDeposit.HistoryDepositActivity;

public class PresenterMenu implements ModelReponsetoModelMenu {
    ModelMenu modelMenu;
    PresenterReponsetoViewMenu callback;
    Context context;
    Activity activity;

    public PresenterMenu(PresenterReponsetoViewMenu callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelMenu = new ModelMenu(context, this);
    }

    public void setVisibility(TableRow nguoidangtinbatdongsan){
        modelMenu.initInfoDataUser(nguoidangtinbatdongsan);
    }

    public void initButtonIntent(int requestcode) {
        modelMenu.initButtonIntent(requestcode);
    }

    public void FetchLogout() {
        modelMenu.FetchLogout();
    }

    @Override
    public void IntentView() {
        callback.IntentView();
    }

    @Override
    public void onFetchLogout() {
        callback.onFetchLogout();
    }

    @Override
    public void onsetVisibility() {
        callback.onsetVisibility();
    }
}
