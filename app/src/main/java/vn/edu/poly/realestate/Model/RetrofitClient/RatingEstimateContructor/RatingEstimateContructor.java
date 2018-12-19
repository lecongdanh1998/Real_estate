package vn.edu.poly.realestate.Model.RetrofitClient.RatingEstimateContructor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingEstimateContructor {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("rsinfo_id")
        @Expose
        private String rsinfoId;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("price_buy")
        @Expose
        private Object priceBuy;
        @SerializedName("price_deposit")
        @Expose
        private Object priceDeposit;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getRsinfoId() {
            return rsinfoId;
        }

        public void setRsinfoId(String rsinfoId) {
            this.rsinfoId = rsinfoId;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Object getPriceBuy() {
            return priceBuy;
        }

        public void setPriceBuy(Object priceBuy) {
            this.priceBuy = priceBuy;
        }

        public Object getPriceDeposit() {
            return priceDeposit;
        }

        public void setPriceDeposit(Object priceDeposit) {
            this.priceDeposit = priceDeposit;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
}
