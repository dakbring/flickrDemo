package com.codepath.apps.restclienttemplate.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences{

    /** holds application shared preferences. */
    private final SharedPreferences mSharedPrefs;
    /** holds application shared preferences editor. */
    private final SharedPreferences.Editor mSharedPrefsEditor;

    public UserPreferences(Context context) {
        mSharedPrefs = context.getSharedPreferences(UserPreferencesConstants.PREFS_NAME,0);
        mSharedPrefsEditor = mSharedPrefs.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPrefs;
    }

    public void setUserAccessToken(String token){
        mSharedPrefsEditor.putString(UserPreferencesConstants.USER_ACCESS_TOKEN, token);
        mSharedPrefsEditor.commit();
    }

    public String getUserAccessToken(){
        return mSharedPrefs.getString(UserPreferencesConstants.USER_ACCESS_TOKEN, "");
    }

}
