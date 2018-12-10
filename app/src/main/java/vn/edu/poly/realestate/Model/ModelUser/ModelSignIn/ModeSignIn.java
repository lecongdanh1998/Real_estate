package vn.edu.poly.realestate.Model.ModelUser.ModelSignIn;

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

public class ModeSignIn {
    ModelReponseToPresenterSignIn callback1;
    Context context;

    public ModeSignIn(ModelReponseToPresenterSignIn callback1, Context context) {
        this.callback1 = callback1;
        this.context = context;
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
                } else {
                    BaseActivity.editorUser = BaseActivity.dataLoginUser.edit();
                    BaseActivity.editorUser.putString("useremail",email);
                    BaseActivity.editorUser.commit();
                    BaseActivity.editorInfo = BaseActivity.dataLoginInfo.edit();
                    BaseActivity.editorInfo.putInt("position", 0);
                    BaseActivity.editorInfo.commit();
                    callback1.onSignInSuccess();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
