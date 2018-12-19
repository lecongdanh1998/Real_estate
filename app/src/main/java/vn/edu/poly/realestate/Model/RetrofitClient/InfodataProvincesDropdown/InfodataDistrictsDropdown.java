package vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InfodataDistrictsDropdown {
    @SerializedName("data")
    @Expose
    private ArrayList<DataAddressDictrict> data;

    public ArrayList<DataAddressDictrict> getData() {
        return data;
    }

    public void setData(ArrayList<DataAddressDictrict> data) {
        this.data = data;
    }
}
