package vn.edu.poly.realestate.Model;

import java.io.Serializable;

public class ListViewMainActivityContructor implements Serializable {
    String avatar,username,images,title,address,deposit,depositBuy,Sell,Phone,Email;

    public ListViewMainActivityContructor() {

    }

    public ListViewMainActivityContructor(String avatar, String username, String images, String title, String address, String deposit, String depositBuy, String sell) {
        this.avatar = avatar;
        this.username = username;
        this.images = images;
        this.title = title;
        this.address = address;
        this.deposit = deposit;
        this.depositBuy = depositBuy;
        Sell = sell;
    }

    public ListViewMainActivityContructor(String avatar, String username, String images, String title, String address, String deposit, String depositBuy, String sell, String phone, String email) {
        this.avatar = avatar;
        this.username = username;
        this.images = images;
        this.title = title;
        this.address = address;
        this.deposit = deposit;
        this.depositBuy = depositBuy;
        Sell = sell;
        Phone = phone;
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDepositBuy() {
        return depositBuy;
    }

    public void setDepositBuy(String depositBuy) {
        this.depositBuy = depositBuy;
    }

    public String getSell() {
        return Sell;
    }

    public void setSell(String sell) {
        Sell = sell;
    }
}
