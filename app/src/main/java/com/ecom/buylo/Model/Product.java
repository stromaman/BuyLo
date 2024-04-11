package com.ecom.buylo.Model;

public class Product {
    Integer Image;
    String Name;
    String Mrp;
    String Discount;

    public  Product(Integer image,String name,String mrp,String discount)
    {
        this.Image=image;
        this.Name=name;
        this.Mrp=mrp;
        this.Discount=discount;
    }

    public Integer getImage(){
        return Image;
    }
    public void setImage(Integer image){
        this.Image=image;
    }

    public String getName(){
        return Name;
    }

    public void  setName(String name){
        this.Name=name;
    }

    public String getMrp(){
        return Mrp;
    }

    public void  setMrp(String mrp){
        this.Mrp=mrp;
    }

    public String getDiscount(){
        return Discount;
    }

    public void  setDiscount(String discount){
        this.Discount=discount;
    }
}
