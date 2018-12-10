package vn.edu.poly.realestate.Model.ModelMenu;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.HistoryDeposit.HistoryDepositActivity;
import vn.edu.poly.realestate.View.User.SignInActivity;

public class ModelMenu {
    Context context;
    ModelReponsetoModelMenu callback;
    Activity activity;
    public ModelMenu(Context context, ModelReponsetoModelMenu callback) {
        this.context = context;
        this.callback = callback;
        this.activity = (Activity) context;
    }


    public void initButtonIntent(int requestcode){
        if(requestcode == 0){
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    context);
            alertDialog2.setTitle("Thoát ứng dụng...");
            alertDialog2.setMessage("Bạn có chắc chắn muốn thoát khỏi ứng dụng không?");
            alertDialog2.setIcon(R.drawable.ic_exit_to_app_black_24dp);
            alertDialog2.setPositiveButton("Có",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
                            BaseActivity.editorInfo.putInt("position", 0);
                            BaseActivity.editorInfo.commit();
                            activity.finish();
                        }
                    });
            alertDialog2.setNegativeButton("Không",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            alertDialog2.show();
        }
        if(requestcode == 1){
            intentView(MainActivity.class,1);
        }
        callback.IntentView();
    }


    public void FetchLogout(){
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
                        intentView(SignInActivity.class,0);
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

    private void intentView(Class c, int requestcode) {
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
        if(requestcode == 0){
            activity.overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
        }
        if (requestcode == 1){
            activity.overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
        }
        activity.finish();
    }

}
