package krogerist.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dcpdev on
 * 9/27/15.
 */
@Data
public class YellowTag {
    private Integer rank;
    private String customerId;
    private String upc;
    private String basePrice;
    private String promoPrice;
    private String itemDescription;
    private String imageUrl;


    public YellowTag() {

    }

    public YellowTag(Integer rank, String customerId, String upc, String basePrice, String promoPrice, String itemDescription, String imageUrl ) {
        this.rank = rank;
        this.customerId = customerId;
        this.upc = upc;
        this.basePrice = basePrice;
        this.promoPrice = promoPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
    }

    public Integer getRank() {
        return rank;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUpc() {
        return upc;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }


//    @Override
//    public String toString() {
//        return "rank: " + rank + ", customerId: " + customerId + ", upc: " + upc + ", basePrice: " + basePrice +
//                ", promoPrice: " + promoPrice + ", itemDescription: " + itemDescription + ", imageUrl: " + imageUrl;
//    }


}
