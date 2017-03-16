package net.xylphid.betclic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ApiClientCredential;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends RootActivity {

    private static final String publicKey = "q5oWRBgSvVUkPSCWGCNPnnSzaYwhwBHx";
    private static final String privateKey = "M4fHHjugSvdjxz3dSYD1LzZbvsjTVLqfTXhS8Ng0ij9TF5Wz9DNG6W0SO7ZrXT6PDiBJ22Mxk49jgXzPP1xB5dzScZD4IMjbPzXAirNugH9SGio59zClwASoI9JAgmqy";
//    MessageDigest md = MessageDigest.getInstance("SHA");

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;

    public LandingActivity() throws NoSuchAlgorithmException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tbHeader);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }
    }

    public void onClickLogin(View view){
        if(email.getText().toString()!=null && password.getText().toString()!=null){

            ApiClientCredential apiCredential = encrypt();
            apiCredential.email = email.getText().toString();
            apiCredential.password = password.getText().toString();
            Log.d("TEST", String.format("[%s] %d, %s", apiCredential.apiKey, apiCredential.ts, apiCredential.ctrl));

            Log.d("Email ----> ", email.getText().toString());
            Log.d("Password ----> ", password.getText().toString());

            LoginAPIService.get().login(apiCredential.email,apiCredential.password, apiCredential.apiKey, apiCredential.ts, apiCredential.ctrl).enqueue(new Callback<String>() {
//            LoginAPIService.get().login(email.getText().toString(),password.getText().toString(), apiCredential.apiKey, apiCredential.ts, apiCredential.ctrl).enqueue(new Callback<String>() {
//            LoginAPIService.get().login(apiCredential).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Log.d("TEST", response.body());
                    } else {
                        Log.d("error", "error api authenticate");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(LandingActivity.this, "Network fail, but why ?", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public ApiClientCredential encrypt(){
        ApiClientCredential credential = new ApiClientCredential();
        credential.apiKey = publicKey;

        Date now = new Date();
        credential.ts = new Long(now.getTime()/1000).intValue();

        byte[] bufferStr = new String(credential.apiKey +privateKey+credential.ts).getBytes();
        byte[] secure = null;
        StringBuffer buffer = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            // Allocate room for the hash
            byte[] hash = new byte[sha.getDigestLength()];
            // Calculate hash
            sha.reset();
            sha.update(bufferStr);

            secure = sha.digest();
            // Create hex-string
            buffer = new StringBuffer(secure.length * 2);
            for (int i = 0; i < secure.length; i++) {
                int intVal = secure[i] & 0xff;
                if (intVal < 0x10) {
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(intVal));
            }

            credential.ctrl = buffer.toString();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        return credential;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header, menu);
        return true;
    }
}
