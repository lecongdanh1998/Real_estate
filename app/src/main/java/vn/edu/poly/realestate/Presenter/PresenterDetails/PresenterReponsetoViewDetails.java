package vn.edu.poly.realestate.Presenter.PresenterDetails;

public interface PresenterReponsetoViewDetails {
    void onDetailsFetchDataSuccess(String text, int requestCodeTextview);
    void onBack();
    void onShowDialogHelp();
    void onFetchDataId(String images,int requestcode);
}
