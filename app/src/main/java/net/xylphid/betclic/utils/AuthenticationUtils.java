package net.xylphid.betclic.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

import net.xylphid.betclic.LandingActivity;
import net.xylphid.betclic.R;

public class AuthenticationUtils {

    public static boolean isConnected(Activity activity) {
        //Read securePreference
        SharedPreferences sharedPref = new SecurePreferences(activity, "betclic", activity.getString(R.string.preference_file_key));
        String publicKey = sharedPref.getString(activity.getString(R.string.secure_public), null);
        String privateKey = sharedPref.getString(activity.getString(R.string.secure_private), null);

        // Check keys
        if (publicKey == null || privateKey == null) {
            return false;
        }
        return true;
    }

}
