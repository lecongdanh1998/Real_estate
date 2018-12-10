package vn.edu.poly.realestate.Model.ModelHistoryDeposit;

import vn.edu.poly.realestate.Adapter.ListViewHistoryDepositActivityAdapter;

public interface ModelResponseToPresenterHistoryDeposit {
    void fetchData(ListViewHistoryDepositActivityAdapter adapter);
    void fetchDataSuccess(String message);
    void fetchDataFailure(String error);
}
