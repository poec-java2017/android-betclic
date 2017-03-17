package net.xylphid.betclic.api.binder;

import model.api.response.AuthenticationResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface AuthenticationBinder {

    @FormUrlEncoded
    @POST("/api/authenticate")
    Call<AuthenticationResponse> login(
            @Field("email") String user,
            @Field("password") String password,
            @Field("apiKey") String apiKey,
            @Field("ts") int ts,
            @Field("ctrl") String ctrl
    );
}
