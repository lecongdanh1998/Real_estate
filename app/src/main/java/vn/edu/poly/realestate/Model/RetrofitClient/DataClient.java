package vn.edu.poly.realestate.Model.RetrofitClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.edu.poly.realestate.Model.RetrofitClient.User.User;

public interface DataClient {
    @Headers("api-key: a21n355a5a8ekf9927as247836dcfd9477ddff037b62d1558fe06d735eb04f5eee37ff3f04f2c05f02nks1f3728d7426dde567764b62972efd5e673f7cf8a268")
    @POST("/oauth/token")
    Call<User> LoginData(@Body HashMap<String,String> hashMap);
}
