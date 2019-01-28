package vn.edu.poly.realestate.Model.ModelMaps;

public interface ModelReponsetoPresenterMaps {
    void onButtonData(int requestcode);
    void onButtonTrueFalse(boolean blShow);
    void onEditTextSearch(String str);
    void onFetchDataId(String str, int requestcode);
}
