package com.ecom.buylo.Model;

public class Category {

    Integer Image;
    String Name;

    public  Category(Integer image,String name)
    {
        this.Image=image;
        this.Name=name;

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
}
