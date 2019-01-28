package vn.edu.poly.realestate.Presenter.PresenterTinDang;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Model.ModelDatLichXem.ModelReponsetoPresenterDatLichXem;
import vn.edu.poly.realestate.Model.ModelTinDang.ModelReponsetoPresenterTinDang;
import vn.edu.poly.realestate.Model.ModelTinDang.ModelTinDang;

public class PresenterTinDang implements ModelReponsetoPresenterTinDang {
    PresenterReponsetoViewTinDang callback;
    Context context;
    Activity activity;
    ModelTinDang modelTinDang;
    public PresenterTinDang(PresenterReponsetoViewTinDang callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelTinDang = new ModelTinDang(this,context);
    }

    public void initFetchData(){
        modelTinDang.initFetchData();
    }

    @Override
    public void onFecthDataAdapter(ListViewMainActivityAdapter adapter) {
        callback.onFecthDataAdapter(adapter);
    }
}
