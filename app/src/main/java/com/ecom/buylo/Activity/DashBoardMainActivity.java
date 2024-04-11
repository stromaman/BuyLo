package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecom.buylo.Fragment.AccountFragment;
import com.ecom.buylo.Fragment.CategoryFragment;
import com.ecom.buylo.Fragment.HomeMainFragment;
import com.ecom.buylo.Fragment.WishListFragment;
import com.ecom.buylo.R;
import com.skydoves.elasticviews.ElasticCardView;

public class DashBoardMainActivity extends AppCompatActivity {
    LinearLayout cardHome,cardProduct,myWishLayout,cardAccount;
    ImageView imageHome,imageProduct,imageWish,imageAccount;
    TextView txtHome,txtProduct,txtWish,txtAccount;
    FrameLayout framelayout;
    ElasticCardView product,whishList,account,dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_main);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#d1ebed"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use WindowInsetsControllerCompat
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.setAppearanceLightStatusBars(true);
        } else {
            // For older APIs, use FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS and FLAG_LIGHT_STATUS_BAR
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        framelayout = findViewById(R.id.framelayout);

        cardHome = findViewById(R.id.cardHome);
        cardProduct = findViewById(R.id.cardProduct);
        cardAccount = findViewById(R.id.cardAccount);

        imageHome = findViewById(R.id.imageHome);
        imageProduct = findViewById(R.id.imageProduct);
        imageWish = findViewById(R.id.imageWish);
        imageAccount = findViewById(R.id.imageAccount);

        txtHome = findViewById(R.id.txtHome);
        txtWish = findViewById(R.id.txtWish);
        txtProduct = findViewById(R.id.txtProduct);
        txtAccount = findViewById(R.id.txtAccount);

        dashboard = findViewById(R.id.dashboard);
        product = findViewById(R.id.product);
        account = findViewById(R.id.account);
        whishList = findViewById(R.id.whishList);


        CallFragment(new HomeMainFragment(),"-1");

        DashboardView();


        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallFragment(new HomeMainFragment(),"-1");

                Glide.with(getApplicationContext()).load(R.drawable.ic_baseline_home_24).into(imageHome);
                txtHome.setTextColor(Color.parseColor("#E91E63"));

                Glide.with(getApplicationContext()).load(R.drawable.category).into(imageProduct);
                txtProduct.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.user).into(imageAccount);
                txtAccount.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.heart).into(imageWish);
                txtWish.setTextColor(Color.parseColor("#FF000000"));
            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallFragment(new CategoryFragment(),"-1");

                Glide.with(getApplicationContext()).load(R.drawable.home).into(imageHome);
                txtHome.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.ic_category).into(imageProduct);
                txtProduct.setTextColor(Color.parseColor("#E91E63"));

                Glide.with(getApplicationContext()).load(R.drawable.user).into(imageAccount);
                txtAccount.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.heart).into(imageWish);
                txtWish.setTextColor(Color.parseColor("#FF000000"));;
            }
        });

        whishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallFragment(new WishListFragment(),"-1");

                Glide.with(getApplicationContext()).load(R.drawable.home).into(imageHome);
                txtHome.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.category).into(imageProduct);
                txtProduct.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.user).into(imageAccount);
                txtAccount.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.ic_heart).into(imageWish);
                txtWish.setTextColor(Color.parseColor("#E91E63"));;
            }
        });

        cardAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallFragment(new AccountFragment(),"-1");

                Glide.with(getApplicationContext()).load(R.drawable.home).into(imageHome);
                txtHome.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.category).into(imageProduct);
                txtProduct.setTextColor(Color.parseColor("#FF000000"));

                Glide.with(getApplicationContext()).load(R.drawable.ic_user).into(imageAccount);
                txtAccount.setTextColor(Color.parseColor("#E91E63"));

                Glide.with(getApplicationContext()).load(R.drawable.heart).into(imageWish);
                txtWish.setTextColor(Color.parseColor("#FF000000"));
            }
        });
    }







    public void CallFragment(Fragment fragment, String pos)
    {
        Bundle bundle = new Bundle();
        bundle.putString("position",pos);
        /*    Toast.makeText(this, "pos: "+pos, Toast.LENGTH_SHORT).show();*/
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void DashboardView()
    {
        CallFragment(new HomeMainFragment(),"-1");

        Glide.with(getApplicationContext()).load(R.drawable.ic_baseline_home_24).into(imageHome);
        txtHome.setTextColor(Color.parseColor("#E91E63"));

        Glide.with(getApplicationContext()).load(R.drawable.category).into(imageProduct);
        txtProduct.setTextColor(Color.parseColor("#FF000000"));

        Glide.with(getApplicationContext()).load(R.drawable.user).into(imageAccount);
        txtAccount.setTextColor(Color.parseColor("#FF000000"));

        Glide.with(getApplicationContext()).load(R.drawable.heart).into(imageWish);
        txtWish.setTextColor(Color.parseColor("#FF000000"));
    }
}