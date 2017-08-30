package com.zscat.storm.recommend.test.order;
  
import java.util.ArrayList;  
import java.util.Date;  
  
public class ordersBean {  
  
    Date createTime = null;  
    String number = "";  
    String paymentNumber = "";  
    Date paymentDate = null;  
    String merchantName = "";  
    ArrayList<skusBean> skuGroup = null;  
    float totalPrice = 0;  
    float discount = 0;  
    float paymentPrice = 0;  
      
    public Date getCreateTime() {  
        return createTime;  
    }  
    public void setCreateTime(Date createTime) {  
        this.createTime = createTime;  
    }  
    public String getNumber() {  
        return number;  
    }  
    public void setNumber(String number) {  
        this.number = number;  
    }  
    public String getPaymentNumber() {  
        return paymentNumber;  
    }  
    public void setPaymentNumber(String paymentNumber) {  
        this.paymentNumber = paymentNumber;  
    }  
    public Date getPaymentDate() {  
        return paymentDate;  
    }  
    public void setPaymentDate(Date paymentDate) {  
        this.paymentDate = paymentDate;  
    }  
    public String getMerchantName() {  
        return merchantName;  
    }  
    public void setMerchantName(String merchantName) {  
        this.merchantName = merchantName;  
    }  
    public ArrayList<skusBean> getSkuGroup() {  
        return skuGroup;  
    }  
    public void setSkuGroup(ArrayList<skusBean> skuGroup) {  
        this.skuGroup = skuGroup;  
    }  
    public float getTotalPrice() {  
        return totalPrice;  
    }  
    public void setTotalPrice(float totalPrice) {  
        this.totalPrice = totalPrice;  
    }  
    public float getDiscount() {  
        return discount;  
    }  
    public void setDiscount(float discount) {  
        this.discount = discount;  
    }  
    public float getPaymentPrice() {  
        return paymentPrice;  
    }  
    public void setPaymentPrice(float paymentPrice) {  
        this.paymentPrice = paymentPrice;  
    }  
      
      
} 