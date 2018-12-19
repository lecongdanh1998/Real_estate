package vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Infodatadeposit {
//    @SerializedName("data")
//    @Expose
//    private Data[] data;
//
//    public Data[] getData() {
//        return data;
//    }
//
//    public void setData(Data[] data) {
//        this.data = data;
//    }

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }


}
