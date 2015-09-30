package krogerist.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dcpdev on 9/29/15.
 */
@Data
public class WeeklyAdItem {

    private String prodCode;
    private String circularItemId;
    private Integer adIdRank;
    private String itemDescription;
    private String imageUrl;
    private Integer itemRank;

    public WeeklyAdItem(){

    }

    public WeeklyAdItem(String prodCode, String circularItemId, Integer adIdRank, String itemDescription, String imageUrl, Integer itemRank) {
        this.prodCode = prodCode;
        this.circularItemId = circularItemId;
        this.adIdRank = adIdRank;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.itemRank = itemRank;
    }
//    @Override
//    public String toString() {
//        return "prodCode: " + prodCode + ", circularItemId: " + circularItemId + ", adIdRank: " + adIdRank
//                + "itemDescription: " + itemDescription + ", imageUrl: " + imageUrl + ", itemRank: " + itemRank;
//
//    }


}
