package vn.edu.poly.realestate.Model.RetrofitClient.UpLoadImage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfodataUploadImage {
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("id")
    @Expose
    private String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
