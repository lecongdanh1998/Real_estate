package vn.edu.poly.realestate.View.Screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Presenter.PresenterSplashScreen.PresenterReponsetoViewSplashScreen;
import vn.edu.poly.realestate.Presenter.PresenterSplashScreen.PresenterSplashScreen;
import vn.edu.poly.realestate.R;

public class SplashScreenActivity extends BaseActivity implements PresenterReponsetoViewSplashScreen {
    ImageView imagesLogo;
    PresenterSplashScreen presenterSplashScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        initControl();
        initData();
    }

    private void initData() {
        presenterSplashScreen = new PresenterSplashScreen(this,this,this);
        presenterSplashScreen.Revecied(imagesLogo);
    }

    private void initControl() {
        imagesLogo =  findViewById(R.id.img_splashScreen);
    }

    @Override
    public void onSplashScreen() {
    }
}
