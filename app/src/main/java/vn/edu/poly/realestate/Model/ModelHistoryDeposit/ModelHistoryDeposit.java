package vn.edu.poly.realestate.Model.ModelHistoryDeposit;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.ListViewHistoryDepositActivityAdapter;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;

public class ModelHistoryDeposit {
    private ModelResponseToPresenterHistoryDeposit callback;
    private Context context;
    private Activity activity;
    private ArrayList<ListViewMainActivityContructor> arrayList2;
    private ListViewHistoryDepositActivityAdapter adapter2;

    public ModelHistoryDeposit(ModelResponseToPresenterHistoryDeposit callback, Context context) {
        this.callback = callback;
        this.context = context;
//        this.activity = (Activity) context;
        arrayList2 = new ArrayList<>();
    }

    public void handleFetchData(){
        arrayList2.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.25"
                , "1111"
        ));
        arrayList2.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.253"
                , "2587"
        ));
        arrayList2.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.2"
                , "3300"
        ));
        arrayList2.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.1"
                , "4340"
        ));
        arrayList2.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.1"
                , "53000"
        ));
        adapter2 = new ListViewHistoryDepositActivityAdapter(arrayList2, context);
        callback.fetchData(adapter2);
    }

    public void handleFetchDataSuccess(){
        callback.fetchDataSuccess("render oke");
    }

    public void handlefetchDataFailure(){
        callback.fetchDataFailure("render failure");
    }
}
