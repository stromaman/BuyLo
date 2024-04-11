package com.ecom.buylo.Activity;

import static android.app.AppOpsManager.MODE_ALLOWED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import com.ecom.buylo.Adapter.ProductAdapter;
import com.ecom.buylo.Model.Product;
import com.ecom.buylo.R;
import com.ecom.buylo.Utils.GPSTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewShopActivity extends AppCompatActivity {
    RecyclerView relatedProductRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shop);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));
        relatedProductRecycler=findViewById(R.id.relatedProductRecycler);

        ArrayList<Product> seasons= new ArrayList<>();
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        seasons.add(new Product(R.drawable.tshirt,"Men Tshirts Black","808","728"));
        relatedProductRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        relatedProductRecycler.setHasFixedSize(true);
        ProductAdapter adapter2 =new ProductAdapter(getApplicationContext(),seasons);
        relatedProductRecycler.setAdapter(adapter2);
    }

    public static class utils {
        private String EXTRA_LAST_APP = "EXTRA_LAST_APP";
        private Context context;
        public static String LAT = "";
        public static String LANG = "";
        public static String STAT = "";
        public static String CIT = "";



        public static boolean checkPermission(Context ctx){
            AppOpsManager appOpsManager = (AppOpsManager) ctx.getSystemService(APP_OPS_SERVICE);
            int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), ctx.getPackageName());
            return mode==MODE_ALLOWED;
        }

       /* UsageStatsManager usageStatsManager;
        public String getLauncherTopApp(){
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            }
            if (Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP){
                List<ActivityManager.RunningTaskInfo> taskInfoList = manager.getRunningTasks(1);
                if (null!= taskInfoList && !taskInfoList.isEmpty()){
                    return taskInfoList.get(0).topActivity.getPackageName();
                }
            }else{
                long endTime = System.currentTimeMillis();
                long beginTime = endTime - 10000;
                String result = "";
            }
        }*/

        public static String getGPSLocation(Context context) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions((Activity) (context), new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, 3);
                return "";
            } else {
                GPSTracker gps = new GPSTracker(context);

                // Check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    /*------- To get city name from coordinates -------- */
                    String cityName = null;
                    String stateName = null;
                    Geocoder gcd = new Geocoder(context, Locale.getDefault());
                    List<Address> addresses;
                    try {
                        addresses = gcd.getFromLocation(latitude,
                                longitude, 1);
                        if (addresses.size() > 0) {
                            System.out.println(addresses.get(0).getLocality());
                            cityName = addresses.get(0).getLocality();
                            stateName = addresses.get(0).getAddressLine(0);
                            Log.e("Location>>>", ""+addresses.get(0).getAddressLine(0)+" .... "+ addresses.get(0).getSubLocality() + " .... "+addresses.get(0).getSubAdminArea()+ " .... "+addresses.get(0).getPremises()+ " .... "+ addresses.get(0).getFeatureName());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    LAT = String.valueOf(latitude);
                    LANG = String.valueOf(longitude);
                    STAT = stateName;
                    CIT = cityName;
                    //   MyPreferences.getInstance(context).saveLastLoc(String.valueOf(latitude),String.valueOf(longitude),stateName, cityName);

                   // String s = ("http://maps.google.com/maps?q=loc:"+LAT+","+LANG);
                    String s = (LAT+","+LANG);
                    // String s = latitude + "  " + longitude;
                    return s;
                    // \n is for new line
                    //Toast.makeText(((CustomerDashboardActivity)getContext()), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+"\n"+cityName, Toast.LENGTH_LONG).show();
                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                    return "";
                }
            }

        }
    }
}