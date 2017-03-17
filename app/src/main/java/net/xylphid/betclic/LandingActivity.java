package net.xylphid.betclic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.securepreferences.SecurePreferences;

import net.xylphid.betclic.api.service.AuthenticationService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.api.request.AuthenticationRequest;
import model.api.response.AuthenticationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    private static final String publicKey = "K2ZaSYczA13ppgXetAofyBFk0oJKB7o5";
    private static final String privateKey = "POXTy7dit0WFQ5YtHMEqocsS9xagfoYbFgvFGRXWy3QY1diohZCFu4S1Th8vGbl3rtLB8874t1E5NI27QucZm3MeZS73RmCwv9dH3rS0af63DU2LOzBxxviUDEbeHjTi";

    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.login) Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);
    }

    public void onClickLogin(View view){
        if(email.getText().toString()!=null && password.getText().toString()!=null){

            AuthenticationRequest apiCredential = encrypt();
            apiCredential.email = email.getText().toString();
            apiCredential.password = password.getText().toString();
            Log.d("TEST", String.format("[%s] %d, %s", apiCredential.apiKey, apiCredential.ts, apiCredential.ctrl));

            Log.d("Email ----> ", email.getText().toString());
            Log.d("Password ----> ", password.getText().toString());

            AuthenticationService.get().login(apiCredential.email,apiCredential.password, apiCredential.apiKey, apiCredential.ts, apiCredential.ctrl).enqueue(new Callback<AuthenticationResponse>() {
                @Override
                public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                    if (response.isSuccessful()) {
                        AuthenticationResponse authResponse = response.body();

                        //Register to securePreference.
                        //SharedPreferences sharedPref = LandingActivity.this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                        SharedPreferences sharedPref = new SecurePreferences(LandingActivity.this, "betclic", getString(R.string.preference_file_key));
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.secure_public), authResponse.publicKey);
                        editor.putString(getString(R.string.secure_private), authResponse.privateKey);
                        editor.commit();

                        //Read securePreference


                        Log.d("TEST", authResponse.publicKey);
                        startActivity(new Intent(LandingActivity.this, EventActivity.class));
                    } else {
                        Log.d("error", "error api authenticate");
                    }
                }

                @Override
                public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                    Toast.makeText(LandingActivity.this, "Network fail, but why ?", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public AuthenticationRequest encrypt(){
        AuthenticationRequest credential = new AuthenticationRequest();
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


}
