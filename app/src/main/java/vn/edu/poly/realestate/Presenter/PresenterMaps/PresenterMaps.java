package vn.edu.poly.realestate.Presenter.PresenterMaps;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import vn.edu.poly.realestate.Model.ModelMaps.ModelMapsData;
import vn.edu.poly.realestate.Model.ModelMaps.ModelReponsetoPresenterMaps;

public class PresenterMaps implements ModelReponsetoPresenterMaps {
    PresenterReponsetoViewMaps callback;
    ModelMapsData modelMapsData;
    Context context;
    Activity activity;

    public PresenterMaps(PresenterReponsetoViewMaps callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelMapsData = new ModelMapsData(this,context);
    }

    public void initFetchData(){
        modelMapsData.initFetchDataId();
    }

    public void initButtonData(int requestcode) {
        modelMapsData.initButtonData(requestcode);
    }

    public void initEditSearch(final String search) {
        modelMapsData.initEditSearch(search);
    }

    @Override
    public void onButtonData(int requestcode) {
        callback.onButtonData(requestcode);
    }

    @Override
    public void onButtonTrueFalse(boolean blShow) {
        callback.onButtonTrueFalse(blShow);
    }

    @Override
    public void onEditTextSearch(String str) {
        callback.onEditTextSearch(str);
    }

    @Override
    public void onFetchDataId(String str, int requestcode) {
        callback.onFetchDataId(str,requestcode);
    }
}
