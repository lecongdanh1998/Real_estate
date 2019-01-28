package vn.edu.poly.realestate.Presenter.PresenterFilter;

import android.app.Activity;
import android.content.Context;
import android.widget.Spinner;

import vn.edu.poly.realestate.Model.ModelFilter.ModelFilter;
import vn.edu.poly.realestate.Model.ModelFilter.ModelReponsetoPresenterFilter;

public class PresenterFilter implements ModelReponsetoPresenterFilter {
    PresenterReponsetoViewFilter callback;
    Context context;
    Activity activity;
    ModelFilter modelFilter;
    public PresenterFilter(PresenterReponsetoViewFilter callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelFilter = new ModelFilter(this,context);
    }
    public void initButtonDataIcon(int requestcode) {
        modelFilter.initButtonDataIcon(requestcode);
    }
    public void initDataDistrictsDropdown(final Spinner spin_District) {
        modelFilter.initDataDistrictsDropdown(spin_District);
    }

    public void initDataChonhuong(final Spinner spin_chonhuong) {
        modelFilter.initDataChonhuong(spin_chonhuong);
    }

    @Override
    public void onButtonDataIcon(int requestcode) {
        callback.onButtonDataIcon(requestcode);
    }

    @Override
    public void onDataDistrictsDropdown() {
        callback.onDataDistrictsDropdown();
    }

    @Override
    public void onDataChonhuong() {
        callback.onDataChonhuong();
    }
}
