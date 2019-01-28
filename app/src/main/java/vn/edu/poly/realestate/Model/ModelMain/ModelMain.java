package vn.edu.poly.realestate.Model.ModelMain;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Adapter.ListViewMainActivityAdapter;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Infodatadeposit;
import vn.edu.poly.realestate.Model.RetrofitClient.InsertData.InsertData;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.FilterRealEstate.FilterActivity;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.MenuActivity;
import vn.edu.poly.realestate.View.Menu.Menu_danhsachchothamdinh.ThamDinhActivity;
import vn.edu.poly.realestate.View.Menu.Menu_nguoidangtinbatdongsan.Dangtinbatdongsan.PostActivity;
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

        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status", "APPROVAL");
        final Call<Infodatadeposit> callback1 = dataClient.Infodatadeposit(hashMap);
        callback1.enqueue(new Callback<Infodatadeposit>() {
            @Override
            public void onResponse(Call<Infodatadeposit> call, Response<Infodatadeposit> response) {
                if (response.body() == null) {
                } else {
                    adapter = new ListViewMainActivityAdapter(response.body().getData(), context, clickNe);
                    callback.onFecthDataAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Infodatadeposit> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();

            }
        });
        clickNe = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data = (Data)view.getTag();
                callback.onAddress(data.getAddress());
            }
        };
    }

    public void IntentData(Data contructor, int position) {
        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
        BaseActivity.editorInfo.putInt("position", position);
        BaseActivity.editorInfo.putString("id", contructor.getId());
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
                        BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
                        BaseActivity.editorInfo.putInt("position", 0);
                        BaseActivity.editorInfo.commit();
                        BaseActivity.editorUser = BaseActivity.dataLoginUser.edit();
                        BaseActivity.editorUser.putString("access_token", "");
                        BaseActivity.editorUser.commit();
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
        if (requestcode == 1) {
            activity.overridePendingTransition(R.anim.exit_on_right, R.anim.stay_still);
            activity.finish();
        } else if (requestcode == 2) {
            activity.overridePendingTransition(R.anim.enter_from_right, R.anim.stay_still);
        }

    }


    public void ShowDialogHelp() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_question);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
        BaseActivity.editorInfo.putInt("position", 0);
        BaseActivity.editorInfo.commit();
        initButtonIntent(0);
        callback.onExit();
    }

    public void initButtonIntent(int requestcode) {
        if (requestcode == 0) {


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
        } else if (requestcode == 1) {
            intentView(MenuActivity.class, requestcode);
            callback.onButtonIntent();
        } else if (requestcode == 2) {
            intentView(FilterActivity.class, requestcode);
            callback.onButtonIntent();
        }

    }


}
