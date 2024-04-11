package com.ecom.buylo.Model;

public class RentCategory {

    Integer Image;
    String Name;
    String Money;

    public  RentCategory(Integer image,String name,String Money)
    {
        this.Image=image;
        this.Name=name;
        this.Money=Money;

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

    public String getMoney(){
        return Money;
    }

    public void  setMoney(String money){
        this.Money=money;
    }

}
