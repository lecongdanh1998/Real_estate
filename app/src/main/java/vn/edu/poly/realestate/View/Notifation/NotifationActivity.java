package vn.edu.poly.realestate.View.Notifation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.edu.poly.realestate.Adapter.AdapterNotification;
import vn.edu.poly.realestate.Presenter.PresenterNotification.PresenterNotification;
import vn.edu.poly.realestate.Presenter.PresenterNotification.PresenterReponsetoViewNotification;
import vn.edu.poly.realestate.R;

public class NotifationActivity extends Fragment implements PresenterReponsetoViewNotification {
    View view;
    ListView listView;
    PresenterNotification presenterNotification;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_notifation, container, false);
        initControl();
        initData();
        initOnClick();
        return view;
    }

    private void initControl() {
        listView = view.findViewById(R.id.listview_notifation);
    }

    private void initData() {
        presenterNotification = new PresenterNotification(this,getContext());
        presenterNotification.initFetchData();
    }

    private void initOnClick() {
    }

    @Override
    public void onFetchData(AdapterNotification adapter) {
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
