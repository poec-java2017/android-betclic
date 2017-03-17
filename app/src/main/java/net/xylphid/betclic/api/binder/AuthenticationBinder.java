package net.xylphid.betclic.api.binder;

import model.ApiClientCredential;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;


public interface AuthenticationBinder {

    @FormUrlEncoded
    @POST("/api/authenticate")
    Call<String> login(
//            @Body ApiClientCredential credential
            @Field("email") String user,
            @Field("password") String password,
            @Field("apiKey") String apiKey,
            @Field("ts") int ts,
            @Field("ctrl") String ctrl
    );
}
