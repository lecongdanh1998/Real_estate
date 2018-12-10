package vn.edu.poly.realestate.View.Menu.HistoryDeposit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import vn.edu.poly.realestate.Adapter.ListViewHistoryDepositActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterHistoryDeposit.PresenterHistoryDeposit;
import vn.edu.poly.realestate.Presenter.PresenterHistoryDeposit.PresenterResponseToViewHistoryDeposit;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;

public class HistoryDepositActivity extends BaseActivity implements PresenterResponseToViewHistoryDeposit {

    ListView listView_history_deposit;
    PresenterHistoryDeposit presenterHistoryDeposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_deposit);
        initControl();
        initData();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
        finish();
    }

    public void initControl() {
        listView_history_deposit = findViewById(R.id.listView_history_deposit);
    }

    public void initData() {
        presenterHistoryDeposit = new PresenterHistoryDeposit(this,this);
        presenterHistoryDeposit.reciveData();
    }

    @Override
    public void fetchData(ListViewHistoryDepositActivityAdapter adapter) {
        listView_history_deposit.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fetchDataSuccess(String message) {
        presenterHistoryDeposit.fetchDataSuccess(message);
    }

    @Override
    public void fetchDataFailure(String error) {
        presenterHistoryDeposit.fetchDataFailure(error);
    }
}
