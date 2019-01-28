package vn.edu.poly.realestate.View.TinDang;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Presenter.PresenterTinDang.PresenterReponsetoViewTinDang;
import vn.edu.poly.realestate.Presenter.PresenterTinDang.PresenterTinDang;
import vn.edu.poly.realestate.R;

public class TinDangActivity extends Fragment implements PresenterReponsetoViewTinDang {
    PresenterTinDang presenterTinDang;
    View view;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_tin_dang, container, false);
        initControl();
        initData();
        initOnClick();
        return view;
    }

    private void initControl() {
        listView = view.findViewById(R.id.listview_tindang);
    }

    private void initOnClick() {
    }

    private void initData() {
        presenterTinDang = new PresenterTinDang(this,getContext());
        presenterTinDang.initFetchData();
    }

    @Override
    public void onFecthDataAdapter(ListViewMainActivityAdapter adapter) {
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
