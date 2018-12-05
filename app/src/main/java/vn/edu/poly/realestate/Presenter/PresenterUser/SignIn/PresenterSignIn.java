package vn.edu.poly.realestate.Presenter.PresenterUser.SignIn;

import android.content.Context;

import vn.edu.poly.realestate.Model.ModelUser.ModelSignIn.ModeSignIn;
import vn.edu.poly.realestate.Model.ModelUser.ModelSignIn.ModelReponseToPresenterSignIn;

public class PresenterSignIn implements ModelReponseToPresenterSignIn {
    private ModeSignIn modeSignIn;
    Context context;
    private PresenterReponsetoViewSignIn callback;


    public PresenterSignIn(PresenterReponsetoViewSignIn callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void receivedHanleSignIn(String email, String password){
        modeSignIn = new ModeSignIn(this,context);
        modeSignIn.handleSignIn(email, password);
    }

    @Override
    public void onSignInSuccess() {
        callback.onSignInSuccess();
    }

    @Override
    public void onSignInFailed(String error) {
        callback.onSignInFailed(error);
    }

}
