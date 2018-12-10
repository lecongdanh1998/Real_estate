package vn.edu.poly.realestate.Presenter.PresenterSplashScreen;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import vn.edu.poly.realestate.Model.ModelSplashcreen.ModelReponsetoPresenterSplashcreen;
import vn.edu.poly.realestate.Model.ModelSplashcreen.ModelSplashcreen;
import vn.edu.poly.realestate.R;

public class PresenterSplashScreen implements ModelReponsetoPresenterSplashcreen {
    PresenterReponsetoViewSplashScreen callback;
    Context context;
    ModelSplashcreen modelSplashcreen;
    Activity activity;

    public PresenterSplashScreen(PresenterReponsetoViewSplashScreen callback, Context context,Activity activity) {
        this.callback = callback;
        this.context = context;
        this.activity = activity;
    }

    public void Revecied(ImageView imagesLogo){
        modelSplashcreen = new ModelSplashcreen(this,context,activity);
        modelSplashcreen.Revecied(imagesLogo);
    }


    @Override
    public void onSplashScreen() {
        callback.onSplashScreen();
    }
}
