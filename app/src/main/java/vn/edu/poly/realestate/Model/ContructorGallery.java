package vn.edu.poly.realestate.Model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;

public class ContructorGallery implements Serializable {
    Bitmap imageView;

    public ContructorGallery(Bitmap imageView) {
        this.imageView = imageView;
    }

    public ContructorGallery() {
    }

    public Bitmap getImageView() {
        return imageView;
    }

    public void setImageView(Bitmap imageView) {
        this.imageView = imageView;
    }
}
