package vn.edu.poly.realestate.Model.ModelMain;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.Menu.MenuActivity;
import vn.edu.poly.realestate.View.User.SignInActivity;

public class ModelMain {

    ModelReponsetoPresenterMain callback;
    Context context;
    ArrayList<ListViewMainActivityContructor> arrayList;
    ListViewMainActivityAdapter adapter;
    Activity activity;
    private View.OnClickListener clickNe;
    Dialog dialog;
    int requestcode;

    public ModelMain(ModelReponsetoPresenterMain callback, Context context, Activity activity) {
        this.callback = callback;
        this.context = context;
        this.activity = activity;
        arrayList = new ArrayList<>();
    }

    public void handleFetchData() {

        arrayList.add(new ListViewMainActivityContructor(
                "https://znews-stc.zdn.vn/static/topic/person/trump.jpg"
                , "Donald Trump"
                , "http://nhadatquangtri.vn/images/attachment/48091484024312.jpg"
                , "Bán nhanh lô đất, mặt tiền đường Nguyễn Vức (gần Bộ chỉ huy Quân sự tỉnh)"
                , "109 Hàm Nghi, TP Đông Hà, Quảng Trị (gần Chợ Phường 5)"
                , "0.1"
                , "0.25"
                , "1111"
                , "0964.294.275 (Anh Nhựt)"
                , "danhlc@nks.com"
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
                , "0964.294.275 (Anh Nhựt)"
                , "danhlc@nks.com"
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
                , "0964.294.275 (Anh Nhựt)"
                , "danhlc@nks.com"
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
                , "0964.294.275 (Anh Nhựt)"
                , "danhlc@nks.com"
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
                , "0964.294.275 (Anh Nhựt)"
                , "danhlc@nks.com"
        ));
        adapter = new ListViewMainActivityAdapter(arrayList, context, clickNe);
        callback.onFecthDataAdapter(adapter);
    }

    public void IntentData(ListViewMainActivityContructor contructor, int position) {
        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
        BaseActivity.editorInfo.putInt("position", position);
        BaseActivity.editorInfo.putString("Deposit", contructor.getDeposit());
        BaseActivity.editorInfo.putString("Sell", contructor.getSell());
        BaseActivity.editorInfo.putString("DepositBuy", contructor.getDepositBuy());
        BaseActivity.editorInfo.commit();
        callback.onIntentData();
    }

    public void FetchLogout() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                context);
        alertDialog2.setTitle("Đăng xuất...");
        alertDialog2.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
        alertDialog2.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        alertDialog2.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        BaseActivity.editorUser = BaseActivity.dataLoginUser.edit();
                        BaseActivity.editorUser.putString("useremail", "");
                        BaseActivity.editorUser.commit();
                        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
                        BaseActivity.editorInfo.putInt("position", 0);
                        BaseActivity.editorInfo.commit();
                        intentView(SignInActivity.class);
                    }
                });
        alertDialog2.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
        callback.onFetchLogout();
    }

    private void intentView(Class c) {
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
        activity.finish();
    }


    public void ShowDialogHelp() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_question);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btn_ok_dialogquestion = dialog.findViewById(R.id.btn_ok_dialogquestion);
        btn_ok_dialogquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        callback.onShowDialogHelp();
    }


    public void onExit(int position) {
        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
        BaseActivity.editorInfo.putInt("position", position);
        BaseActivity.editorInfo.commit();
        intentView(MenuActivity.class);
        callback.onExit();
    }


}
