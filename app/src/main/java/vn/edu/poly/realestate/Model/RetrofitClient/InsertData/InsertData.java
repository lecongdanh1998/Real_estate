package vn.edu.poly.realestate.Model.RetrofitClient.InsertData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertData {
    @SerializedName("data")
    @Expose
    private DataUpdate dataUpdate;

    public DataUpdate getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(DataUpdate dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public class DataUpdate {
        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

