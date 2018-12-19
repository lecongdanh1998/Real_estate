package vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataAddressWard {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;

    public DataAddressWard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
