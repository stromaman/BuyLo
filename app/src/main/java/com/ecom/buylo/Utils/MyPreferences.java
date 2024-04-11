package com.ecom.buylo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {
    private static MyPreferences mInstance;
    private static Context mCtx;

    private static final String SHARED_PREFF_NAME = "mysharedpref12";
    public static final String Name = "name";
    public static final String Password = "password";
    public static final String Id = "id";






    public boolean logout() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREFF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
        return true;
    }



    public void saveVendor(Integer id,String name, String password){
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREFF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Name, name);
        editor.putString(Password, password);
        editor.putInt(Id, id);
        editor.apply();
    }


    public MyPreferences(Context context) {
        mCtx = context;
    }

    public static synchronized MyPreferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyPreferences(context);
        }
        return mInstance;
    }

    public int getUserId() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREFF_NAME, Context.MODE_PRIVATE);
        return settings.getInt(Id, -1); // -1 is the default value if the key is not found or cannot be converted to an integer
    }

    public String getUserName() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREFF_NAME, Context.MODE_PRIVATE);
        return settings.getString(Name,null);
    }

    public String getUserPassword() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREFF_NAME, Context.MODE_PRIVATE);
        return settings.getString(Password,null);
    }
}
