package vn.edu.poly.realestate.Model.ModelUser.ModelSignIn;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.User.User;
import vn.edu.poly.realestate.Model.RetrofitClient.UserInfo.UserInfo;

public class ModeSignIn {
    ModelReponseToPresenterSignIn callback1;
    Context context;
    private ProgressDialog progressDialog;

    public ModeSignIn(ModelReponseToPresenterSignIn callback1, Context context) {
        this.callback1 = callback1;
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    public void handleSignIn(String email, String password) {
        if (email.toString().equals("") && password.toString().equals("")) {
            callback1.onSignInFailed("Vui lòng nhập email và password");
        } else if (email.toString().equals("") && !password.toString().equals("")) {
            callback1.onSignInFailed("Vui lòng nhập email");
        } else if (!email.toString().equals("") && password.toString().equals("")) {
            callback1.onSignInFailed("Vui lòng nhập password");
        } else {
            SignIn(email, password);
        }
    }

    public void SignIn(final String email, String password) {
        setContentDialog("Đăng nhập", "Vui lòng chờ");
        progressDialog.show();
        progressDialog.setCancelable(false);
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", email);
        hashMap.put("password", password);
        hashMap.put("grant_type", "password");
        hashMap.put("client_id", "2");
        hashMap.put("client_secret", "rOU4FPWpj36XDlWvNZBn1S39BZaxFpGyAEtBHBLH");
        Call<User> callback = dataClient.LoginData(hashMap);
        callback.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() == null) {
                    Toast.makeText(context, "Email hoặc password không đúng", Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                } else {
                    initInfoDataUser(response.body().getAccess_token());
                    callback1.onSignInSuccess();
                    progressDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
    }

    private void setContentDialog(String title, String messager) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(messager);
    }


    public void initInfoDataUser(final String data) {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Authorization", "Bearer " + data);
        Call<UserInfo> callback = dataClient.UserInfo(hashMap);
        callback.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.body() == null) {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                } else {
                    UserInfo.Data data1 = response.body().getData();
                    BaseActivity.editorUser = BaseActivity.dataLoginUser.edit();
                    BaseActivity.editorUser.putString("access_token", data);
                    BaseActivity.editorUser.commit();
                    BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
                    BaseActivity.editorInfo.putInt("position", 0);
                    BaseActivity.editorInfo.commit();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet Info", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
