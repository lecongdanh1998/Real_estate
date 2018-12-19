package vn.edu.poly.realestate.Model.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPending;
import vn.edu.poly.realestate.Model.RetrofitClient.DuyetTinDang.DuyetTinDang;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataDetails.InfodataDetails;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataDistrictsDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataProvincesDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.InfodataWardsDropdown;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Infodatadeposit;
import vn.edu.poly.realestate.Model.RetrofitClient.InsertData.InsertData;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingEstimateContructor.RatingEstimateContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.UpLoadImage.InfodataUploadImage;
import vn.edu.poly.realestate.Model.RetrofitClient.User.User;
import vn.edu.poly.realestate.Model.RetrofitClient.UserInfo.UserInfo;

public interface DataClient {
    @Headers("api-key: a21n355a5a8ekf9927as247836dcfd9477ddff037b62d1558fe06d735eb04f5eee37ff3f04f2c05f02nks1f3728d7426dde567764b62972efd5e673f7cf8a268")
    @POST("/oauth/token")
    Call<User> LoginData(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/user")
    Call<UserInfo> UserInfo(@HeaderMap HashMap<String, String> hashMap);


    @POST("/api/nks/rsinfos/add")
    Call<InsertData> InsertData(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/rsinfos")
    Call<Infodatadeposit> Infodatadeposit(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/rsinfos")
    Call<ContructorPending> InfodataDanhsachThamdinh(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/rsinfo")
    Call<InfodataDetails> Infodatadetails(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/provinces-dropdown")
    Call<InfodataProvincesDropdown> InfodataProvincesDropdown();

    @POST("/api/nks/districts-dropdown")
    Call<InfodataDistrictsDropdown> InfodataDistrictsDropdown(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/wards-dropdown")
    Call<InfodataWardsDropdown> InfodataWardsDropdown(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/rsinfo/updateImg")
    Call<InfodataUploadImage> InfodataUploadImage(@Body HashMap<String, String> hashMap);


    @POST("/api/nks/rsinfo/ratingInfo")
    Call<RatingContructor> RatingContructor(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/rsinfo/estimate")
    Call<RatingEstimateContructor> RatingEstimateContructor(@Body HashMap<String, String> hashMap);

    @POST("/api/nks/rsinfo/approval")
    Call<DuyetTinDang> DuyetTinDang(@Body HashMap<String, String> hashMap);

}
