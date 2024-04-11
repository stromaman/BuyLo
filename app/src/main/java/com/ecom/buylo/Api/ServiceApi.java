package com.ecom.buylo.Api;

import com.ecom.buylo.Model.request.AddProduct;
import com.ecom.buylo.Model.request.CityRequest;
import com.ecom.buylo.Model.request.DistrictRequest;
import com.ecom.buylo.Model.request.FinalCategory;
import com.ecom.buylo.Model.request.LowCategoryRent;
import com.ecom.buylo.Model.request.StateRequest;
import com.ecom.buylo.Model.request.SubcategoryRent;
import com.ecom.buylo.Model.request.Vendor;
import com.ecom.buylo.Model.request.categoryRent;
import com.ecom.buylo.Model.request.verifyOtp;
import com.ecom.buylo.Model.response.OtpRequest;
import com.ecom.buylo.Model.response.VendorProduct;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceApi {

    @Multipart
    @POST("VendorRegistration")
    Call<Vendor> getRegister(@Part("ShopName") RequestBody ShopName,
                             @Part("Mobileno") RequestBody Mobileno,
                             @Part("WhatsAppno") RequestBody WhatsAppno,
                             @Part("Address") RequestBody Address,
                             @Part("email") RequestBody email,
                             @Part("LatandLong") RequestBody LatandLong,
                             @Part("Stateid") RequestBody Stateid,
                             @Part("Districtid") RequestBody Districtid,
                             @Part("Cityid") RequestBody Cityid,
                             @Part MultipartBody.Part ProfileImage);



    @Multipart
    @POST("AddProduct")
    Call<VendorProduct>getProductRegister(@Part("VendorId") RequestBody VendorId,
                                           @Part("Categoryid") RequestBody Categoryid,
                                           @Part("ProductType") RequestBody ProductType,
                                           @Part("ProductName") RequestBody ProductName,
                                           @Part("productdescription") RequestBody productdescription,
                                           @Part("Mrp") RequestBody Mrp,
                                           @Part("SellingPrice") RequestBody SellingPrice,
                                           @Part("Discount") RequestBody Discount,
                                           @Part("ManufacturingDetails") RequestBody ManufacturingDetails,
                                           @Part MultipartBody.Part ProductImage);




    @POST("StateName")
    Call<StateRequest> getStateList();

    @FormUrlEncoded
    @POST("districtName")
    Call<DistrictRequest> getDistrictList(@Field("StateId") String state_id);

    @FormUrlEncoded
    @POST("CityName")
    Call<CityRequest> getCityList(@Field("districtId") String districtId);


    @GET("CategoryLists")
    Call<categoryRent> getRentCategory();

    @FormUrlEncoded
    @POST("SubCategoryLists")
    Call<SubcategoryRent> getSubcategory(@Field("CategoryId") String CategoryId);

    @FormUrlEncoded
    @POST("Lowcategory")
    Call<LowCategoryRent>getLowcategory(@Field("SubCategoryId") String SubCategoryId);


    @FormUrlEncoded
    @POST("FinalCategoryLists")
    Call<FinalCategory>getFinalCategory(@Field("Lowcategory") String Lowcategory);

    @FormUrlEncoded
    @POST("AllProductsList")
    Call<AddProduct> getProductList(@Field("Vendorid") String Vendorid);



    @FormUrlEncoded
    @POST("GetotpGenerate")
    Call<OtpRequest> getLoginOtp(@Field("mobileNo") String mobileNo);


    @FormUrlEncoded
    @POST("UserRegistration")
    Call<verifyOtp> getUser(@Field("mobileNo") String mobileNo,
                            @Field("enteredOTP")String enteredOTP);

}
