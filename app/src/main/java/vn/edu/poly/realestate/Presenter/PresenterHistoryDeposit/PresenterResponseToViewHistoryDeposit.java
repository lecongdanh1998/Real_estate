package vn.edu.poly.realestate.Presenter.PresenterHistoryDeposit;

import vn.edu.poly.realestate.Adapter.ListViewHistoryDepositActivityAdapter;

public interface PresenterResponseToViewHistoryDeposit {
    void fetchData(ListViewHistoryDepositActivityAdapter adapter);
    void fetchDataSuccess(String message);
    void fetchDataFailure(String error);
}
