package vn.edu.poly.realestate.Model.ModelDetails;

public interface ModelReponsetoPresenterDetails {
    void onDetailsFetchDataSuccess(String text,int requestCodeTextview);
    void onBack();
    void onShowDialogHelp();
    void onFetchDataId(String images,int requestcode);
}
