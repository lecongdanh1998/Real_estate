package vn.edu.poly.realestate.Presenter.PresenterDetails;

import android.content.Context;

import vn.edu.poly.realestate.Model.ModelDetails.ModelDetails;
import vn.edu.poly.realestate.Model.ModelDetails.ModelReponsetoPresenterDetails;
import vn.edu.poly.realestate.Model.ModelUser.ModelSignIn.ModeSignIn;

public class PresenterDetails implements ModelReponsetoPresenterDetails {
    private ModelDetails modelDetails;
    Context context;
    private PresenterReponsetoViewDetails callback;

    public PresenterDetails(Context context, PresenterReponsetoViewDetails callback) {
        this.context = context;
        this.callback = callback;
    }

    public void ReceivedHanleData(String Strdangduoccoc, String Strtiencoc, String Strtienmua) {
        modelDetails = new ModelDetails(this, context);
        modelDetails.handleFetchData(Strdangduoccoc, Strtiencoc, Strtienmua);
    }


    @Override
    public void onDetailsFetchDataSuccess(String text, int requestCodeTextview) {
        callback.onDetailsFetchDataSuccess(text,requestCodeTextview);

    }
}
