package net.xylphid.betclic;


import net.xylphid.betclic.api.binder.AuthenticationBinder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPIService {

    private static AuthenticationBinder service;


    public static AuthenticationBinder get(){
        if(service==null){

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            if(BuildConfig.DEBUG){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(logging);
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://m2i.xylphid.net")
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(AuthenticationBinder.class);
        }
        return service;
    }


}
