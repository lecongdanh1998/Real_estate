package vn.edu.poly.realestate.Model.ModelSplashcreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.Util.ValidateForm;
import vn.edu.poly.realestate.View.MainActivity;
import vn.edu.poly.realestate.View.Menu.MenuActivity;
import vn.edu.poly.realestate.View.Screen.SplashScreenActivity;
import vn.edu.poly.realestate.View.User.SignInActivity;

public class ModelSplashcreen {
    ModelReponsetoPresenterSplashcreen callback;
    Context context;
    private int SPLASH_DISPLAY_LENGTH = 2;
    String access_token;
    Activity activity;
    public ModelSplashcreen(ModelReponsetoPresenterSplashcreen callback, Context context,Activity activity) {
        this.callback = callback;
        this.context = context;
        this.activity = activity;
    }

    public void Revecied(ImageView imagesLogo){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.mytransition);
        imagesLogo.startAnimation(animation);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private class LogoLauncher extends Thread {
        public void run() {
            try {
                sleep(1000 * SPLASH_DISPLAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkDataLogin();
        }
    }

    private void checkDataLogin() {
        access_token = BaseActivity.dataLoginUser.getString("access_token", "");
        if (new ValidateForm().validateTextEmpty(access_token) == false) {
            Intent intent = new Intent(activity,MainActivity.class);
            activity.startActivity(intent);
        } else {
            Intent mainIntent = new Intent(activity, SignInActivity.class);
            activity.startActivity(mainIntent);
        }
        activity.overridePendingTransition(R.anim.enter_from_left, R.anim.stay_still);
        activity.finish();
        callback.onSplashScreen();

    }

}
