package krogerist.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dcpdev on 9/29/15.
 */
@Data
public class WeeklyAd {

    private String customerId;
    private String circularItemId;
    private Integer rank;
    private String startDate;
    private String endDate;
    private String itemTitle;
    private String imageUrl;
    private String priceString;
    private String itemDescription;

    public WeeklyAd(){

    }
    //DIB_CUST_ID,CircularItemId,RANK,STARTDATE,ENDDATE,Item_Title,image_url,price_string,description
    public WeeklyAd(String customerId, String circularItemId, Integer rank, String startDate, String endDate, String itemTitle, String imageUrl, String priceString, String itemDescription){
        this.customerId = customerId;
        this.circularItemId = circularItemId;
        this.rank = rank;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemTitle = itemTitle;
        this.imageUrl = imageUrl;
        this.priceString = priceString;
        this.itemDescription = itemDescription;
    }

    public String getEndDate() {
        return endDate;
    }


    public String getCircularItemId() {
        return circularItemId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public Integer getRank() {
        return rank;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPriceString() {
        return priceString;
    }


//    @Override
//    public String toString() {
//        return "customerId: " + customerId + ", circularItemId: " + circularItemId + ", rank: " + rank
//                + "startDate: " + startDate + ", endDate: " + endDate+ ", itemTitle: " + itemTitle;
//
//
//    }


}
