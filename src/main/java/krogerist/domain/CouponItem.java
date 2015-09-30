package krogerist.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dcpdev on 9/29/15.
 */
@Data
public class CouponItem {

    private Integer rank;
    private String couponOfferId;
    private String customerId;
    private String couponNum;
    private String baseUpc;
    private String itemDescription;
    private String itemSize;
    private String imageUrl;
    private Integer prodRank;

    public CouponItem(){

    }

    public CouponItem(Integer rank, String couponOfferId, String customerId, String couponNum, String baseUpc, String itemDescription, String itemSize, String imageUrl, Integer prodRank){
        this.rank = rank;
        this.couponOfferId = couponOfferId;
        this.customerId = customerId;
        this.couponNum =couponNum;
        this.baseUpc = baseUpc;
        this.itemDescription = itemDescription;
        this.itemSize = itemSize;
        this.imageUrl = imageUrl;
        this.prodRank = prodRank;
    }

//    @Override
//    public String toString() {
//        return "rank: " + rank + ", couponOfferId: " + couponOfferId + ", couponNum: " + couponNum
//                + ", baseUpc: " + baseUpc + ", itemDescription: " + itemDescription
//                + "itemSize: " + itemSize + ", imageUrl: " + imageUrl + ", prodRank: " + prodRank;
//
//    }


}
