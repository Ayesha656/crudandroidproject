package com.example.crudbasicproject;

public class Products {
   public  Products(){
   }

    public Products(String productdescription, String productname, String productprice) {
        this.productdescription = productdescription;
        this.productname = productname;
        this.productprice = productprice;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

   private String productdescription,productname,productprice;

}
