package vn.edu.poly.realestate.Model.ModelUser.ModelSignIn;

public interface ModelReponseToPresenterSignIn {
    void onSignInSuccess();
    void onSignInFailed(String error);
}
