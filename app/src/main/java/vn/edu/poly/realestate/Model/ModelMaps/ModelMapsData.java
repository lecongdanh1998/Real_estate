package vn.edu.poly.realestate.Model.ModelMaps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.DetailsMain.DatLichXem.DatLichXemActivity;

public class ModelMapsData {
    ModelReponsetoPresenterMaps callback;
    Context context;
    Activity activity;
    boolean blShow = false;

    public ModelMapsData(ModelReponsetoPresenterMaps callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }

    public void initFetchDataId(){
        callback.onFetchDataId("", 1);
    }

    public void initButtonData(int requestcode) {
        if (requestcode == 1) {
            callback.onButtonData(1);
        } else if (requestcode == 2) {
            callback.onButtonData(2);
        } else if (requestcode == 3) {
            if (blShow == false) {
                callback.onButtonTrueFalse(blShow);
                blShow = true;
            } else {
                callback.onButtonTrueFalse(blShow);
                blShow = false;
            }
        } else if (requestcode == 4) {
            callback.onButtonData(4);
        } else if (requestcode == 5) {
            callback.onButtonData(5);
        } else if (requestcode == 6) {
            callback.onButtonData(6);
        }
        else if (requestcode == 7) {
            intentView(DatLichXemActivity.class);
//            callback.onButtonData(7);
        }
    }
    private void intentView(Class c) {
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.enter_from_right, R.anim.stay_still);
//        activity.finish();
    }


    public void initEditSearch(final String search) {
        callback.onEditTextSearch(search);

    }
}
