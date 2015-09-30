package krogerist.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dcpdev on 9/29/15.
 */
@Data
public class Favorite {

    private String customerId;
    private String itemNum;
    private Integer rank;
    private String itemDescription;
    private String imageUrl;

    public Favorite()
    {

    }

    public Favorite(String customerId, String itemNum, Integer rank, String itemDescription, String imageUrl){
        this.customerId = customerId;
        this.itemNum = itemNum;
        this.rank = rank;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
    }

    public String getItemNum() {
        return itemNum;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Integer getRank() {
        return rank;
    }

    public String getItemDescription(){
        return itemDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }
//    @Override
//    public String toString() {
//        return "customerId: " + customerId + ", itemNum: " + itemNum + ", rank: " + rank
//                + "itemDescription: " + itemDescription + ", imageUrl: " + imageUrl;
//
//
//    }

}
