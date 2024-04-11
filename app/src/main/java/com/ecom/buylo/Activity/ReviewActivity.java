package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.response.VendorProduct;
import com.ecom.buylo.R;
import com.ecom.buylo.Utils.Constant;
import com.google.android.material.snackbar.Snackbar;
import com.skydoves.elasticviews.ElasticButton;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {
    ElasticButton submitCatlog;
    String userId, finalId, districtId,type,CategoryName,SubCategoryName, productname,productType, description, details, mrp, selling, discount, mamufacture,skuid, size, invetory,imageUri;
    Uri returnUri;
    RelativeLayout parentLayout;
    ImageView imgProfile;
    TextView productSkuid,productInvetary,productSize,productDetails,productMrp,productSelling,productDiscount,productManufacture,productName,productCategory,productSubCategory,productDescription,Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        submitCatlog = findViewById(R.id.submitCatlog);
        parentLayout = findViewById(R.id.parentLayout);
        productDescription=findViewById(R.id.productDescription);
        productDetails=findViewById(R.id.productDetails);
        productSkuid=findViewById(R.id.productSkuid);
        productInvetary=findViewById(R.id.productInvetary);
        productSize=findViewById(R.id.productSize);
        productMrp=findViewById(R.id.productMrp);
        productSelling=findViewById(R.id.productSelling);
        productDiscount=findViewById(R.id.productDiscount);
        productManufacture=findViewById(R.id.productManufacture);
        productName=findViewById(R.id.productName);
        Type=findViewById(R.id.Type);
        imgProfile=findViewById(R.id.imgProfile);
        productCategory=findViewById(R.id.productCategory);
        productSubCategory=findViewById(R.id.productSubCategory);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            type = extra.getString("type");
            if (type.equals("signup")) {
                Intent intent = getIntent();
                 userId = intent.getStringExtra("userId");
                finalId = intent.getStringExtra("stateId");
                CategoryName = intent.getStringExtra("CategoryName");
                 districtId = intent.getStringExtra("districtId");
                SubCategoryName = intent.getStringExtra("SubCategoryName");
                 productname = intent.getStringExtra("productname");
                 description = intent.getStringExtra("description");
                 productType = intent.getStringExtra("productType");
                 mrp = intent.getStringExtra("mrp");
               selling = intent.getStringExtra("selling");
                discount = intent.getStringExtra("discount");
                 mamufacture = intent.getStringExtra("namufacture");
                 size = intent.getStringExtra("size");
                 skuid = intent.getStringExtra("skuid");
                 invetory = intent.getStringExtra("invetory");
                imageUri = intent.getStringExtra("imageUri");

// Image ka URI string se URI object banayein


//                Log.e("ID", userId);
//                Log.e("Category", finalId);
//                Log.e("NAME", productname);
//                Log.e("Description", description);
//                Log.e("Type", productType);
//                Log.e("Mrp", mrp);
//                Log.e("Selling", selling);
//                Log.e("discount", discount);
//                Log.e("manufacture", mamufacture);
//                Log.e("file", imageUri);



                productName.setText(productname);
                productDescription.setText(description);
                Type.setText(productType);
                productMrp.setText(mrp);
                productSelling.setText(selling);
                productDiscount.setText(discount);
                productManufacture.setText(mamufacture);
                productSize.setText(size);
                productCategory.setText(CategoryName);
                if (imageUri != null) {
                    ImageView imgProfile = findViewById(R.id.imgProfile);
                    Glide.with(this)
                            .load(imageUri)
                            .into(imgProfile);
                }

//                if (imageUri != null) {
//                    Glide.with(getApplicationContext())
//                            .load(Constant.IMAGE_URL)
//                            .into(imgProfile);
//                }


            }
            submitCatlog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ReviewActivity.this,MyShopActivity.class);
                    startActivity(intent);
                  //  doUserRegistration(userId, finalId, productname, description, productType, mrp, selling, discount, mamufacture,imageUri);
                }
            });

        }
    }

    private void doUserRegistration(String userId, String finalId,
                                    String productname, String description, String productType, String mrp, String selling, String discount, String mamufacture,  String imageUriString) {

        MultipartBody.Part propertyStartRideImage = null;
        if (returnUri != null) {
            //creating a file
            File filePARCEL = (File) getIntent().getSerializableExtra("imageUri");

            if (filePARCEL != null) {
                RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"), filePARCEL.getAbsoluteFile());
                propertyStartRideImage = MultipartBody.Part.createFormData("ProductImage",
                        filePARCEL.getName(), propertyImage);
            } else {
                RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "");
                propertyStartRideImage = MultipartBody.Part.createFormData("ProductImage", "", attachmentEmpty);
            }
        }

        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<VendorProduct> call = api.getProductRegister(RequestBody.create(MediaType.parse("multipart/form-data"),userId ),
                RequestBody.create(MediaType.parse("multipart/form-data"), finalId),
                RequestBody.create(MediaType.parse("multipart/form-data"), productname),
                RequestBody.create(MediaType.parse("multipart/form-data"), description),
                RequestBody.create(MediaType.parse("multipart/form-data"), productType),
                RequestBody.create(MediaType.parse("multipart/form-data"), mrp),
                RequestBody.create(MediaType.parse("multipart/form-data"), selling),
                RequestBody.create(MediaType.parse("multipart/form-data"), discount),
                RequestBody.create(MediaType.parse("multipart/form-data"), mamufacture),
                propertyStartRideImage


        );


        call.enqueue(new Callback<VendorProduct>() {
            @Override
            public void onResponse(Call<VendorProduct> call, Response<VendorProduct> response) {

                if (response.body() != null) {
                    if (response.body().getResult()) {
                        Intent intent = new Intent(ReviewActivity.this, MyShopActivity.class);
                        Snackbar.make(parentLayout, "" + response.body().getMessage(), Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.parseColor("#12d06b")) // 136afb
                                .setTextColor(Color.WHITE)
                                .show();
                        startActivity(intent);
                    } else {
                        Snackbar.make(parentLayout, "" + response.body().getMessage(), Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
                                .setTextColor(Color.WHITE)
                                .show();
                    }
                } else {
                    Log.d("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<VendorProduct> call, Throwable t) {

                Log.d("FAILURE", t.getLocalizedMessage());
            }
        });
    }


}