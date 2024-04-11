package com.ecom.buylo.Model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductVendor {
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("Cname")
    @Expose
    private String cname;
    @SerializedName("Sname")
    @Expose
    private String sname;
    @SerializedName("ProductType")
    @Expose
    private String productType;
    @SerializedName("ProductImage")
    @Expose
    private String productImage;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductDescription")
    @Expose
    private String productDescription;
    @SerializedName("Mrp")
    @Expose
    private String mrp;
    @SerializedName("SellingPrice")
    @Expose
    private String sellingPrice;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("ManufacturingDetails")
    @Expose
    private String manufacturingDetails;
    @SerializedName("Inventary")
    @Expose
    private String inventary;
    @SerializedName("SKUID")
    @Expose
    private String skuid;
    @SerializedName("Size")
    @Expose
    private String size;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getManufacturingDetails() {
        return manufacturingDetails;
    }

    public void setManufacturingDetails(String manufacturingDetails) {
        this.manufacturingDetails = manufacturingDetails;
    }

    public String getInventary() {
        return inventary;
    }

    public void setInventary(String inventary) {
        this.inventary = inventary;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
