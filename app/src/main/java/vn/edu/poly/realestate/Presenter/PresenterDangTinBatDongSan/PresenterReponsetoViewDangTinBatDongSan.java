package vn.edu.poly.realestate.Presenter.PresenterDangTinBatDongSan;

import vn.edu.poly.realestate.Adapter.AdapterFilterChonhuong;
import vn.edu.poly.realestate.Adapter.AdapterFilterGiayChuQuyen;
import vn.edu.poly.realestate.Adapter.AdapterFilterLoaiDat;
import vn.edu.poly.realestate.Adapter.AdapterFilterPhong;

public interface PresenterReponsetoViewDangTinBatDongSan {
    void onIntentView();
    void onSpinnerAddress(String requestAddress,int requestcode);
    void onDataChonhuong( AdapterFilterChonhuong adapterFilterChonhuong);
    void onDataLoaiDat(AdapterFilterLoaiDat adapterFilterLoaiDat);
    void onDataPhong(AdapterFilterPhong adapterFilterPhong);
    void onDataGiayChuQuyen(AdapterFilterGiayChuQuyen adapterFilterGiayChuQuyen);
    void onFetchAddress(String str);
    void onEditTextSearch(String str);
    void onKeyboard();
}
