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

    public WeeklyAd(){

    }

    public WeeklyAd(String customerId, String circularItemId, Integer rank, String startDate, String endDate, String itemTitle){
        this.customerId = customerId;
        this.circularItemId = circularItemId;
        this.rank = rank;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemTitle = itemTitle;
    }
//    @Override
//    public String toString() {
//        return "customerId: " + customerId + ", circularItemId: " + circularItemId + ", rank: " + rank
//                + "startDate: " + startDate + ", endDate: " + endDate+ ", itemTitle: " + itemTitle;
//
//
//    }


}
