package vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingData {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("comment")
    @Expose
    private Object comment;
    @SerializedName("price_buy")
    @Expose
    private String priceBuy;
    @SerializedName("price_deposit")
    @Expose
    private String priceDeposit;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public String getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(String priceBuy) {
        this.priceBuy = priceBuy;
    }

    public String getPriceDeposit() {
        return priceDeposit;
    }

    public void setPriceDeposit(String priceDeposit) {
        this.priceDeposit = priceDeposit;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
