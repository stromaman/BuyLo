package com.ecom.buylo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecom.buylo.Adapter.FinalCategoryAdapter;
import com.ecom.buylo.Adapter.LowCategoryAdapter;
import com.ecom.buylo.Adapter.RentCategoryAdapter;
import com.ecom.buylo.Adapter.SubCategoryAdapter;
import com.ecom.buylo.Api.ApiClient;
import com.ecom.buylo.Api.ServiceApi;
import com.ecom.buylo.Model.request.FinalCategory;
import com.ecom.buylo.Model.request.LowCategoryRent;
import com.ecom.buylo.Model.request.SubcategoryRent;
import com.ecom.buylo.Model.request.categoryRent;
import com.ecom.buylo.Model.response.FinalCategoryy;
import com.ecom.buylo.Model.response.LowCategory;
import com.ecom.buylo.Model.response.Rent;
import com.ecom.buylo.Model.response.Subcat;
import com.ecom.buylo.Model.response.VendorProduct;
import com.ecom.buylo.R;
import com.ecom.buylo.Utils.InternetValidation;
import com.ecom.buylo.Utils.MyPreferences;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends AppCompatActivity {
    ImageView imgProfile;
    CardView openCamera,shopCard;
    Bitmap bitmapImage;
    File file;
    Uri returnUri;
    ProgressBar progress;
     String product,finalId;
    TextView productTypeTextView;
    public TextView txtState, txtDistrict,txtDistric,txtDistri;
    public String stateId, districtId,CategoryName,SubCategoryName,productId,districId, payment_code;
    MaterialButton addNext,basicNext,goShop;
    LinearLayout addProduct,basicDetails,addVarients,productType;
    RelativeLayout parentLayout;
    CheckBox sellCheckbox,rentCheckbox,bothCheckbox;
    boolean isAllFieldsChecked = false;
    EditText productSkuid,productInvetary,productSize,productMrp,productSelling,productDiscount,productManufacture,productName,productDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

        progress = findViewById(R.id.progress);
        productDescription=findViewById(R.id.productDescription);
        //productDetails=findViewById(R.id.productDetails);
//        productSkuid=findViewById(R.id.productSkuid);
//        productInvetary=findViewById(R.id.productInvetary);
      //  productSize=findViewById(R.id.productSize);
        productMrp=findViewById(R.id.productMrp);
        productSelling=findViewById(R.id.productSelling);
        productDiscount=findViewById(R.id.productDiscount);
        productManufacture=findViewById(R.id.productManufacture);
        productName=findViewById(R.id.productName);
        productType=findViewById(R.id.productType);
        addNext=findViewById(R.id.addNext);
        addProduct=findViewById(R.id.addProduct);
        addVarients=findViewById(R.id.addVarients);
        basicDetails=findViewById(R.id.basicDetails);
        openCamera=findViewById(R.id.openCamera);
        txtState=findViewById(R.id.productCategory);
        txtDistrict=findViewById(R.id.productSubCategory);
        parentLayout=findViewById(R.id.parentLayout);
        basicNext=findViewById(R.id.basicNext);
        goShop=findViewById(R.id.goShop);
        imgProfile=findViewById(R.id.imgProfile);
        sellCheckbox = findViewById(R.id.sell);
        rentCheckbox = findViewById(R.id.rent);
        bothCheckbox = findViewById(R.id.Both);
        progress.setVisibility(View.GONE);
        LinearLayout productTypeLayout = findViewById(R.id.productType);
        productTypeTextView = findViewById(R.id.productTypeTextView);


        sellCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sellCheckbox.isChecked()) {
                    // Sell checkbox is checked
                    productTypeTextView.setText("Sell");
                } else {
                    // Sell checkbox is unchecked
                    productTypeTextView.setText("");
                }
            }
        });

        rentCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rentCheckbox.isChecked()) {
                    // Rent checkbox is checked
                    productTypeTextView.setText("Rent");
                } else {
                    // Rent checkbox is unchecked
                    productTypeTextView.setText("");
                }
            }
        });
        bothCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bothCheckbox.isChecked()) {
                    // Both checkbox is checked
                    productTypeTextView.setText("Both");
                } else {
                    // Both checkbox is unchecked
                    productTypeTextView.setText("");
                }
            }
        });
//        txtState.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (InternetValidation.validation(getApplicationContext())) {
//                    selectStatePopup();
//                } else {
//                    InternetValidation.getNoConnectionDialog(getApplicationContext());
//                }
//
//            }
//        });



        Intent intent = getIntent();
        if (intent != null) {
             product = intent.getStringExtra("product_name");
             finalId = String.valueOf(intent.getIntExtra("product_id",0));
            Log.e("SubCategoryId", "Received category_id: " + product);
            Log.e("SubCategoryId", "Received category_id: " + finalId);
            txtState.setText(product);
        } else {
            Log.e("SubCategoryId", "Intent is null");
        }
        txtState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CatalogActivity.this,CategoryActivity.class);
                startActivity(intent);
            }
        });
//        txtDistrict.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (stateId != null) {
//
//                    if (InternetValidation.validation(CatalogActivity.this)) {
//                        selectDistrictPopup(stateId);
//                    } else {
//                        InternetValidation.getNoConnectionDialog(CatalogActivity.this);
//                    }
//
//                } else {
//                    Snackbar.make(parentLayout, "Please Select Category...!!", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }
//            }
//        });
//        txtDistric.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (districtId != null) {
//
//                    if (InternetValidation.validation(CatalogActivity.this)) {
//                        selectProductPopup(districtId);
//                    } else {
//                        InternetValidation.getNoConnectionDialog(CatalogActivity.this);
//                    }
//
//                } else {
//                    Snackbar.make(parentLayout, "Please Select Category...!!", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }
//            }
//        });
//        txtDistri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (productId != null) {
//
//                    if (InternetValidation.validation(CatalogActivity.this)) {
//                        selectFinalProductPopup(productId);
//                    } else {
//                        InternetValidation.getNoConnectionDialog(CatalogActivity.this);
//                    }
//
//                } else {
//                    Snackbar.make(parentLayout, "Please Select Category...!!", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }
//            }
//        });
        MyPreferences preferences = MyPreferences.getInstance(getApplicationContext());
        Log.e("Username", preferences.getUserName());
        Log.e("Password", preferences.getUserPassword());
        goShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreferences preferences = MyPreferences.getInstance(getApplicationContext());
                isAllFieldsChecked = checkAllFields();
                if(isAllFieldsChecked){
                    Log.e("Username", String.valueOf(preferences.getUserId()));
                    Log.e("Category", finalId);
                    Log.e("productType",  productTypeTextView.getText().toString());
                    Log.e("Name", productName.getText().toString().trim());
                    Log.e("Descrip", productDescription.getText().toString().trim());
                    Log.e("Mrp", productMrp.getText().toString().trim());
                    Log.e("Selling", productSelling.getText().toString().trim());
                    Log.e("Discount", productDiscount.getText().toString().trim());
                    Log.e("Manufacture", productManufacture.getText().toString().trim());
                    doproductRegistration(
                            String.valueOf(preferences.getUserId())
                            ,finalId
                            ,productTypeTextView.getText().toString()
                            , productName.getText().toString()
                            , productDescription.getText().toString().trim()
                            , productMrp.getText().toString().trim()
                            , productSelling.getText().toString().trim()
                            , productDiscount.getText().toString().trim()
                            , productManufacture.getText().toString().trim());
                }
            }
        });

//        txtState.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (InternetValidation.validation(getApplicationContext())) {
//                    selectStatePopup();
//                } else {
//                    InternetValidation.getNoConnectionDialog(getApplicationContext());
//                }
//
//            }
//        });
//
//        txtDistrict.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (stateId != null) {
//
//                    if (InternetValidation.validation(CatalogActivity.this)) {
//                        selectDistrictPopup(stateId);
//                    } else {
//                        InternetValidation.getNoConnectionDialog(CatalogActivity.this);
//                    }
//
//                } else {
//                    Snackbar.make(parentLayout, "Please Select Category...!!", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(Color.parseColor("#EA2525")) // 136afb
//                            .setTextColor(Color.WHITE)
//                            .show();
//                }
//            }
//        });

        addNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addProduct.getVisibility()== View.VISIBLE){
                    addProduct.setVisibility(View.GONE);
                    basicDetails.setVisibility(View.VISIBLE);
                    basicNext.setVisibility(View.VISIBLE);
                }
            }
        });

        basicNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(basicDetails.getVisibility()== View.VISIBLE){
                    basicDetails.setVisibility(View.GONE);
                    addVarients.setVisibility(View.VISIBLE);
                    goShop.setVisibility(View.VISIBLE);
                }
            }
        });

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetValidation.validation(CatalogActivity.this)) {
                    imageChooser();
                } else {
                    InternetValidation.getNoConnectionDialog(CatalogActivity.this);
                }
            }
        });



    }

    private boolean checkAllFields() {
        if (productName.length() == 0) {
            Snackbar.make(parentLayout, "Please Enter Name", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.parseColor("#EA2525"))
                    .setTextColor(Color.WHITE)
                    .show();
            return false;

            
        }
        return true;
    }


    private void doproductRegistration(String userId, String finalId,String productType, String productname, String description,
                                        String mrp, String selling, String discount, String namufacture) {
        progress.setVisibility(View.VISIBLE);
        MultipartBody.Part propertyStartRideImage = null;
        if (returnUri != null) {
            //creating a file
            File filePARCEL = new File(getRealPathFromUri(returnUri));

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
                RequestBody.create(MediaType.parse("multipart/form-data"), productType),
                RequestBody.create(MediaType.parse("multipart/form-data"), productname),
                RequestBody.create(MediaType.parse("multipart/form-data"), description),
                RequestBody.create(MediaType.parse("multipart/form-data"), mrp),
                RequestBody.create(MediaType.parse("multipart/form-data"), selling),
                RequestBody.create(MediaType.parse("multipart/form-data"), discount),
                RequestBody.create(MediaType.parse("multipart/form-data"), namufacture),
                propertyStartRideImage);



        call.enqueue(new Callback<VendorProduct>() {
            @Override
            public void onResponse(Call<VendorProduct> call, Response<VendorProduct> response) {
                progress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().getResult()) {
                        Intent intent = new Intent(CatalogActivity.this, ReviewActivity.class);
                        intent.putExtra("type", "signup");
                        intent.putExtra("userId", userId);
                        intent.putExtra("stateId", finalId);
                        intent.putExtra("productType", productType);
                        intent.putExtra("productname", productname);
                        intent.putExtra("description", description);
                        intent.putExtra("mrp", mrp);
                        intent.putExtra("selling", selling);
                        intent.putExtra("discount", discount);
                        intent.putExtra("namufacture", namufacture);
                        if (returnUri != null) {
                            intent.putExtra("imageUri", returnUri.toString());
                        }
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
                progress.setVisibility(View.GONE);
                Log.d("FAILURE", t.getLocalizedMessage());
            }
        });
    }



    public BottomSheetDialog selectStateDialog;
    TextView stateHeading;
    ProgressBar stateProgress;
    RecyclerView stateRecycler;
    RentCategoryAdapter stateListAdapter;
    EditText searchState;
    List<Rent> stateList = new ArrayList<>();

    public void selectStatePopup() {
        selectStateDialog = new BottomSheetDialog(CatalogActivity.this);
        selectStateDialog.setContentView(R.layout.custom_select_area_layout);
        selectStateDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
        selectStateDialog.getWindow().setTransitionBackgroundFadeDuration(10000);

        stateHeading = selectStateDialog.findViewById(R.id.txtAreaHeading);
        stateProgress = selectStateDialog.findViewById(R.id.areaProgress);
        stateRecycler = selectStateDialog.findViewById(R.id.areaRecycler);
        searchState = selectStateDialog.findViewById(R.id.edtSearchArea);

        stateRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        stateProgress.setVisibility(View.GONE);
        stateHeading.setText("Select Your Product Category");
        getStateList();

        searchState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                stateFilter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        selectStateDialog.show();
        selectStateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void stateFilter(String statename) {
        List<Rent> newStateList = new ArrayList<>();
        for (Rent state : stateList) {
            if (state.getCname().toLowerCase().trim().contains(statename.toLowerCase().trim())) {
                newStateList.add(state);
            }
        }
       // stateListAdapter.filterList(newStateList);
    }

    public void getStateList() {
        stateProgress.setVisibility(View.VISIBLE);
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<categoryRent> call = api.getRentCategory();
        call.enqueue(new Callback<categoryRent>() {
            @Override
            public void onResponse(Call<categoryRent> call, Response<categoryRent> response) {
                stateProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        stateList = response.body().getData();
                        stateListAdapter = new RentCategoryAdapter(CatalogActivity.this
                                , CatalogActivity.this
                                ,null
                                ,null
                                , response.body().getData()
                                ,null);
                        stateRecycler.setAdapter(stateListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<categoryRent> call, Throwable t) {
                stateProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public BottomSheetDialog selectDistrictDialog;
    TextView districtHeading;
    ProgressBar districtProgress;
    RecyclerView districtRecycler;
    EditText searchDistrict;
    SubCategoryAdapter districtListAdapter;
    List<Subcat> districtList = new ArrayList<>();

    public void selectDistrictPopup(String stateId) {
        selectDistrictDialog = new BottomSheetDialog(CatalogActivity.this);
        selectDistrictDialog.setContentView(R.layout.custom_select_area_layout);
        selectDistrictDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
        selectDistrictDialog.getWindow().setTransitionBackgroundFadeDuration(10000);

        districtHeading = selectDistrictDialog.findViewById(R.id.txtAreaHeading);
        districtProgress = selectDistrictDialog.findViewById(R.id.areaProgress);
        districtRecycler = selectDistrictDialog.findViewById(R.id.areaRecycler);
        searchDistrict = selectDistrictDialog.findViewById(R.id.edtSearchArea);

        districtRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        districtProgress.setVisibility(View.GONE);
        districtHeading.setText("Select Your Product Subcategory");
        getDistrictList(stateId);

        searchDistrict.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                districtFilter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        selectDistrictDialog.show();
        selectDistrictDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void districtFilter(String districtname) {
        List<Subcat> newDistrictList = new ArrayList<>();
        for (Subcat district : districtList) {
            if (district.getSname().toLowerCase().trim().contains(districtname.toLowerCase().trim())) {
                newDistrictList.add(district);
            }
        }
        //districtListAdapter.filterList(newDistrictList);
    }

    public void getDistrictList(String stateId) {
        districtProgress.setVisibility(View.VISIBLE);
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<SubcategoryRent> call = api.getSubcategory(stateId);
        call.enqueue(new Callback<SubcategoryRent>() {
            @Override
            public void onResponse(Call<SubcategoryRent> call, Response<SubcategoryRent> response) {
                districtProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        districtList = response.body().getData();
                        districtListAdapter = new SubCategoryAdapter(getApplicationContext()
                                , CatalogActivity.this
                                , null
                                , null
                                , response.body().getData());
                        districtRecycler.setAdapter(districtListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<SubcategoryRent> call, Throwable t) {
                districtProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }


    private void imageChooser() {
        ImagePicker.Companion.with(this)
                /*.saveDir(new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), ""+getResources().getString(R.string.app_name)))*/
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(300)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(400, 550)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            returnUri = data.getData();
            if (returnUri != null) {
                try {
                    file = new File(getRealPathFromUri(returnUri));
                    bitmapImage = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), returnUri);
                    imgProfile.setImageBitmap(bitmapImage);
                    Log.e("FILE PATH: ", file.toString());
                    Log.e("BITMAP PATH: ", bitmapImage.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getRealPathFromUri(final Uri uri) { // function for file path from uri,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(CatalogActivity.this, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(CatalogActivity.this, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(CatalogActivity.this, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(CatalogActivity.this, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Drive.
     */
    private static boolean isGoogleDriveUri(Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority()) ||
                "com.google.android.apps.docs.storage.legacy".equals(uri.getAuthority());
    }

    private static boolean isWhatsAppUri(Uri uri) {
        return "com.whatsapp.provider.media".equals(uri.getAuthority());
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                String value = cursor.getString(index);
                if (value.startsWith("content://") || !value.startsWith("/") && !value.startsWith("file://")) {
                    return null;
                }

                return value;
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;

    }


    public BottomSheetDialog selectDistricDialog;
    TextView districHeading;
    ProgressBar districProgress;
    RecyclerView districRecycler;
    EditText searchDistric;
    LowCategoryAdapter districListAdapter;
    List<LowCategory> districList = new ArrayList<>();



    private void selectProductPopup(String districtId) {
        selectDistricDialog = new BottomSheetDialog(CatalogActivity.this);
        selectDistricDialog.setContentView(R.layout.custom_select_area_layout);
        selectDistricDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
        selectDistricDialog.getWindow().setTransitionBackgroundFadeDuration(10000);

        districHeading = selectDistricDialog.findViewById(R.id.txtAreaHeading);
        districProgress = selectDistricDialog.findViewById(R.id.areaProgress);
        districRecycler = selectDistricDialog.findViewById(R.id.areaRecycler);
        searchDistric = selectDistricDialog.findViewById(R.id.edtSearchArea);

        districRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        districProgress.setVisibility(View.GONE);
        districHeading.setText(txtDistrict.getText());
        getDistricList(districtId);

        searchDistric.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                districFilter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        selectDistricDialog.show();
        selectDistricDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void districFilter(String districtname) {
        List<LowCategory> newDistrictList = new ArrayList<>();
        for (LowCategory district : districList) {
            if (district.getSScName().toLowerCase().trim().contains(districtname.toLowerCase().trim())) {
                newDistrictList.add(district);
            }
        }
        districListAdapter.filterList(newDistrictList);
    }

    public void getDistricList(String districtId) {
        districtProgress.setVisibility(View.VISIBLE);
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<LowCategoryRent> call = api.getLowcategory(districtId);
        call.enqueue(new Callback<LowCategoryRent>() {
            @Override
            public void onResponse(Call<LowCategoryRent> call, Response<LowCategoryRent> response) {
                districtProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        districList = response.body().getData();
                        districListAdapter = new LowCategoryAdapter(getApplicationContext()
                                , CatalogActivity.this
                                , null
                                , response.body().getData());
                        districtRecycler.setAdapter(districListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<LowCategoryRent> call, Throwable t) {
                districtProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }



    public BottomSheetDialog selectDistriDialog;
    TextView distriHeading;
    ProgressBar distriProgress;
    RecyclerView distriRecycler;
    EditText searchDistri;
    FinalCategoryAdapter distriListAdapter;
    List<FinalCategoryy> distriList = new ArrayList<>();

    private void selectFinalProductPopup(String productId) {
        selectDistriDialog = new BottomSheetDialog(CatalogActivity.this);
        selectDistriDialog.setContentView(R.layout.custom_select_area_layout);
        selectDistriDialog.getWindow().findViewById(R.id.contantLayout).setBackgroundColor(Color.TRANSPARENT);
        selectDistriDialog.getWindow().setTransitionBackgroundFadeDuration(10000);

        distriHeading = selectDistriDialog.findViewById(R.id.txtAreaHeading);
        distriProgress = selectDistriDialog.findViewById(R.id.areaProgress);
        distriRecycler = selectDistriDialog.findViewById(R.id.areaRecycler);
        searchDistri = selectDistriDialog.findViewById(R.id.edtSearchArea);

        distriRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        distriProgress.setVisibility(View.GONE);
        distriHeading.setText(txtDistrict.getText());
        getDistriList(productId);

        searchDistri.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                distriFilter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        selectDistriDialog.show();
        selectDistriDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }



    public void distriFilter(String distritname) {
        List<FinalCategoryy> newDistrictList = new ArrayList<>();
        for (FinalCategoryy distric : distriList) {
            if (distric.getConcatenatedName().toLowerCase().trim().contains(distritname.toLowerCase().trim())) {
                newDistrictList.add(distric);
            }
        }
        distriListAdapter.filterList(newDistrictList);
    }

    public void getDistriList(String productId) {
        districtProgress.setVisibility(View.VISIBLE);
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<FinalCategory> call = api.getFinalCategory(productId);
        call.enqueue(new Callback<FinalCategory>() {
            @Override
            public void onResponse(Call<FinalCategory> call, Response<FinalCategory> response) {
                districtProgress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        distriList = response.body().getData();
                        distriListAdapter = new FinalCategoryAdapter(getApplicationContext()
                                , CatalogActivity.this
                                , null
                                , response.body().getData());
                        distriRecycler.setAdapter(distriListAdapter);
                    } else {

                    }
                } else {
                    Log.e("BODY", "Body is null");
                }
            }

            @Override
            public void onFailure(Call<FinalCategory> call, Throwable t) {
                districtProgress.setVisibility(View.GONE);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }
}