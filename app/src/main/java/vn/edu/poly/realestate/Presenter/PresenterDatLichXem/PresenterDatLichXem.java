package vn.edu.poly.realestate.Presenter.PresenterDatLichXem;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.realestate.Model.ModelDatLichXem.ModelReponsetoPresenterDatLichXem;

public class PresenterDatLichXem implements ModelReponsetoPresenterDatLichXem {
    PresenterReponsetoViewDatLichXem callback;
    Context context;
    Activity activity;

    public PresenterDatLichXem(PresenterReponsetoViewDatLichXem callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }
}
