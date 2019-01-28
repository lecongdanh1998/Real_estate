package vn.edu.poly.realestate.Model.ModelDatLichXem;

import android.app.Activity;
import android.content.Context;

public class ModelDatlichXem {
    ModelReponsetoPresenterDatLichXem callback;
    Context context;
    Activity activity;

    public ModelDatlichXem(ModelReponsetoPresenterDatLichXem callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }
}
