package vn.edu.poly.realestate.Model.ModelMain;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;

public class ModelMain {
    ModelReponsetoPresenterMain callback;
    Context context;
    ArrayList<ListViewMainActivityContructor> arrayList;
    ListViewMainActivityAdapter adapter;
    public ModelMain(ModelReponsetoPresenterMain callback, Context context) {
        this.callback = callback;
        this.context = context;
        arrayList = new ArrayList<>();
    }

    public void handleFetchData(){
        arrayList.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.25"
                , "1111"
        ));
        arrayList.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.253"
                , "2587"
        ));
        arrayList.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.2"
                , "3300"
        ));
        arrayList.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.1"
                , "4340"
        ));
        arrayList.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.1"
                , "53000"
        ));
        adapter = new ListViewMainActivityAdapter(arrayList, context);
        callback.onFecthDataAdapter(adapter);
    }

    public void IntentData(ListViewMainActivityContructor contructor){
        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
        BaseActivity.editorInfo.putString("Deposit", contructor.getDeposit());
        BaseActivity.editorInfo.putString("Sell", contructor.getSell());
        BaseActivity.editorInfo.putString("DepositBuy", contructor.getDepositBuy());
        BaseActivity.editorInfo.commit();
        callback.onIntentData();
    }

}
