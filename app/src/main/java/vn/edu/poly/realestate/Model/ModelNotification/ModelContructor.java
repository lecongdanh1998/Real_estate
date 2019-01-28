package vn.edu.poly.realestate.Model.ModelNotification;

import java.io.Serializable;

public class ModelContructor implements Serializable {
    String image,title,time;

    public ModelContructor(String image, String title, String time) {
        this.image = image;
        this.title = title;
        this.time = time;
    }


    public ModelContructor() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
