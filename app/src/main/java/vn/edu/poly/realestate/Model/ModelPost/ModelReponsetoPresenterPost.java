package vn.edu.poly.realestate.Model.ModelPost;

import android.content.Intent;

public interface ModelReponsetoPresenterPost {
    void onIntentView();
    void onSpinnerAddress(int requestAddress,int requestcode);
    void onEditTextArea(String area,int requestcode);
    void onEditTextPrice(String error,int requestcode);
    void onFetchCamera(Intent takePictureIntent,int camera);
    void onFetchGallery(Intent imageDownload);
    void onFetchInsertData(String str,String error,int requestcode);
    void onFetchUpLoad(String str);
}
