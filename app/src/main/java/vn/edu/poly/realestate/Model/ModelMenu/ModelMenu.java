package vn.edu.poly.realestate.Model.ModelMenu;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.UserInfo.UserInfo;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.HistoryDeposit.HistoryDepositActivity;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.ThamDinhActivity;
import vn.edu.poly.realestate.View.Menu.Menu_nguoidangtinbatdongsan.Dangtinbatdongsan.PostActivity;
import vn.edu.poly.realestate.View.User.SignInActivity;

public class ModelMenu {
    Context context;
    ModelReponsetoModelMenu callback;
    Activity activity;
    String status;
    String access_token;

    public ModelMenu(Context context, ModelReponsetoModelMenu callback) {
        this.context = context;
        this.callback = callback;
        this.activity = (Activity) context;
    }


    public void initButtonIntent(int requestcode) {
        if (requestcode == 0) {
            intentView(MainActivity.class, 1);
//            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
//                    context);
//            alertDialog2.setTitle("Thoát ứng dụng...");
//            alertDialog2.setMessage("Bạn có chắc chắn muốn thoát khỏi ứng dụng không?");
//            alertDialog2.setIcon(R.drawable.ic_exit_to_app_black_24dp);
//            alertDialog2.setPositiveButton("Có",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
//                            BaseActivity.editorInfo.putInt("position", 0);
//                            BaseActivity.editorInfo.commit();
//                            activity.finish();
//                        }
//                    });
//            alertDialog2.setNegativeButton("Không",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//            alertDialog2.show();
        }
        if (requestcode == 1) {
            BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
            BaseActivity.editorInfo.putInt("position", 0);
            BaseActivity.editorInfo.commit();
            intentView(MainActivity.class, 1);
        } else if (requestcode == 2) {
            intentView(PostActivity.class, 1);
        } else if (requestcode == 3) {
            intentView(ThamDinhActivity.class, 1);
        }

        callback.IntentView();
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
                        BaseActivity.editorUser.putString("access_token", "");
                        BaseActivity.editorUser.commit();
                        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
                        BaseActivity.editorInfo.putInt("position", 0);
                        BaseActivity.editorInfo.commit();
                        intentView(SignInActivity.class, 0);
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
        activity.finish();
        if (requestcode == 0) {
            activity.overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
        }
        if (requestcode == 1) {
            activity.overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
        }
    }

    public void initInfoDataUser(final TableRow nguoidangtinbatdongsan) {
        access_token = BaseActivity.dataLoginUser.getString("access_token", "");
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Authorization", "Bearer " + access_token);
        Call<UserInfo> callback1 = dataClient.UserInfo(hashMap);
        callback1.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.body() == null) {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                } else {
                    UserInfo.Data data = response.body().getData();
                    setVisibility(nguoidangtinbatdongsan,
                            data.getRole()
                    );
                    callback.onsetVisibility();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet Info", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVisibility(TableRow nguoidangtinbatdongsan, String role) {
        if (role.toString().equals("appraisers")) {
            //appraisers
        } else if (role.toString().equals("admin")) {
            //admin
        }


    }

}
