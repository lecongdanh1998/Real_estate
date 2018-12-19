package vn.edu.poly.realestate.Model.RetrofitClient.InfodataDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;

public class InfodataDetails {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
