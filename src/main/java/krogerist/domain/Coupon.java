package krogerist.domain;

import lombok.*;

/**
 * Created by dcpdev on 9/29/15.
 */
@Data
public class Coupon {

    private Integer rank;
    private String couponOfferId;
    private String customerId;
    private String couponNum;
    private String score;
    private String baseUpc;
    private String itemDescription;
    private String details;
    private String couponImage;
    private String brand;

    public Coupon(){

    }

    public Coupon(Integer rank, String couponOfferId, String customerId, String couponNum, String score, String baseUpc, String itemDescription, String details, String couponImage, String brand){
        this.rank = rank;
        this.couponOfferId = couponOfferId;
        this.customerId = customerId;
        this.couponNum = couponNum;
        this.score = score;
        this.baseUpc = baseUpc;
        this.itemDescription = itemDescription;
        this.details = details;
        this.couponImage = couponImage;
        this.brand = brand;
    }

    public String getCouponOfferId() {
        return couponOfferId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public String getScore() {
        return score;
    }

    public String getBaseUpc() {
        return baseUpc;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getDetails() {
        return details;
    }

    public String getCouponImage() {
        return couponImage;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getRank() {
        return rank;
    }

//    @Override
//    public String toString() {
//        return "rank: " + rank + ", couponOfferId: " + couponOfferId + ", couponNum: " + couponNum
//                + "score: " + score + ", baseUpc: " + baseUpc + ", itemDescription: " + itemDescription
//                + "details: " + details + ", couponImage: " + couponImage + ", brand: " + brand;
//
//    }

}
