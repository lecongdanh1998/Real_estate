package vn.edu.poly.realestate.Presenter.PresenterMaps;

public interface PresenterReponsetoViewMaps {
    void onButtonData(int requestcode);
    void onButtonTrueFalse(boolean blShow);
    void onEditTextSearch(String str);
    void onFetchDataId(String str, int requestcode);
}
