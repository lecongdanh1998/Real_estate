package vn.edu.poly.realestate.View.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterUser.SignIn.PresenterReponsetoViewSignIn;
import vn.edu.poly.realestate.Presenter.PresenterUser.SignIn.PresenterSignIn;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.MenuActivity;

public class SignInActivity extends BaseActivity implements View.OnClickListener, PresenterReponsetoViewSignIn {
    String screen;
    Button btn_SignIn;
    AppCompatEditText edt_password_signIn, edt_user_signIn;
    private ProgressBar progressBar;
    private SharedPreferences pref;
    String email;
    String password;
    private PresenterSignIn presenterSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initControl();
        initData();
        initOnClick();
    }

    private void initData() {
        screen = dataLoginScreen.getString("ScreenSignIn", "");
        presenterSignIn = new PresenterSignIn(this,this);
    }

    private void initOnClick() {
        btn_SignIn.setOnClickListener(this);
        edt_password_signIn.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    SignIn();
                }
                return false;
            }
        });
    }

    private void initControl() {
        edt_password_signIn = findViewById(R.id.edt_password_signIn);
        btn_SignIn = findViewById(R.id.btn_SignIn);
        edt_user_signIn = findViewById(R.id.edt_user_signIn);
        edt_password_signIn.setTypeface(Typeface.DEFAULT);
        edt_password_signIn.setTransformationMethod(new PasswordTransformationMethod());
    }


    private void intentView(Class c) {
        Intent intent = new Intent(SignInActivity.this, c);
        startActivity(intent);
        if (screen.toString().equals("1")) {
            finish();
        } else {

        }
        finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_SignIn:
                SignIn();
                break;
        }
    }

    private void SignIn(){
        email = edt_user_signIn.getText().toString();
        password = edt_password_signIn.getText().toString();
        presenterSignIn.receivedHanleSignIn(email, password);
    }

    @Override
    public void onSignInSuccess() {
        intentView(MainActivity.class);
    }

    @Override
    public void onSignInFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


}
