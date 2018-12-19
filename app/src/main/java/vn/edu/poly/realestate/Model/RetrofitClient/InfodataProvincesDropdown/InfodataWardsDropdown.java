package vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InfodataWardsDropdown {
    @SerializedName("data")
    @Expose
    private ArrayList<DataAddressWard> data;

    public ArrayList<DataAddressWard> getData() {
        return data;
    }

    public void setData(ArrayList<DataAddressWard> data) {
        this.data = data;
    }
}
