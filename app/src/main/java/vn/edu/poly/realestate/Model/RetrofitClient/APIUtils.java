package vn.edu.poly.realestate.Model.RetrofitClient;

import vn.edu.poly.realestate.Server.BaseURL;

public class APIUtils {
    public static final  String Base_Url = "http://demo.vnlead.webstarterz.com";
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
