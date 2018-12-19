package vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;

public class InfodataProvincesDropdown {
    @SerializedName("data")
    @Expose
    private ArrayList<DataAddress> data;

    public ArrayList<DataAddress> getData() {
        return data;
    }

    public void setData(ArrayList<DataAddress> data) {
        this.data = data;
    }
}
