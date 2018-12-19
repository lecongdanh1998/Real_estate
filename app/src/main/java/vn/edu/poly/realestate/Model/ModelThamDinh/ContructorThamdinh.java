package vn.edu.poly.realestate.Model.ModelThamDinh;

import java.io.Serializable;

public class ContructorThamdinh implements Serializable {
    String avatar, images, title, address, Sell, Buy;
    float star;

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public ContructorThamdinh(String avatar, String images, String title, String address, String sell, String buy, float star) {
        this.avatar = avatar;
        this.images = images;
        this.title = title;
        this.address = address;
        this.Sell = sell;
        this.Buy = buy;
        this.star = star;
    }

    public ContructorThamdinh() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSell() {
        return Sell;
    }

    public void setSell(String sell) {
        Sell = sell;
    }

    public String getBuy() {
        return Buy;
    }

    public void setBuy(String buy) {
        Buy = buy;
    }
}
