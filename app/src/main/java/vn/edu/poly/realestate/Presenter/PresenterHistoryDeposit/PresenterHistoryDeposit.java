package vn.edu.poly.realestate.Presenter.PresenterHistoryDeposit;

import android.content.Context;

import vn.edu.poly.realestate.Adapter.ListViewHistoryDepositActivityAdapter;
import vn.edu.poly.realestate.Model.ModelHistoryDeposit.ModelHistoryDeposit;
import vn.edu.poly.realestate.Model.ModelHistoryDeposit.ModelResponseToPresenterHistoryDeposit;

public class PresenterHistoryDeposit implements ModelResponseToPresenterHistoryDeposit {

    private ModelHistoryDeposit modelHistoryDeposit;
    private Context context;
    private PresenterResponseToViewHistoryDeposit callback;

    public PresenterHistoryDeposit(Context context, PresenterResponseToViewHistoryDeposit callback) {
        this.context = context;
        this.callback = callback;
        modelHistoryDeposit = new ModelHistoryDeposit(this, context);
    }

    public void reciveData(){
        modelHistoryDeposit.handleFetchData();
    }

    @Override
    public void fetchData(ListViewHistoryDepositActivityAdapter adapter) {
        callback.fetchData(adapter);
    }

    @Override
    public void fetchDataSuccess(String message) {
        callback.fetchDataSuccess(message);
    }

    @Override
    public void fetchDataFailure(String error) {
        callback.fetchDataFailure(error);
    }
}
