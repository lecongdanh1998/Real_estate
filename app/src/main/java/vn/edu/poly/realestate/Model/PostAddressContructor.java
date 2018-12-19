package vn.edu.poly.realestate.Model;

import java.io.Serializable;

public class PostAddressContructor implements Serializable {
    String title;

    public PostAddressContructor(String title) {
        this.title = title;
    }

    public PostAddressContructor() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
