package krogerist.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dcpdev on 9/27/15.
 */
@Data
public class BusinessRule {

    private Integer rank;
    private String science;
    private Integer scienceRank;

    public BusinessRule()
    {

    }

    public BusinessRule(Integer rank, String science, Integer scienceRank)
    {
        this.rank = rank;
        this.science = science;
        this.scienceRank = scienceRank;
    }

    public String getScience() {
        return science;
    }

//    public BusinessRule(BusinessRule businessRule, String science)
//    {
//        businessRule.setScience(science);
//    }
//
//
//    public void setRank(Integer rank) {
//        this.rank = rank;
//    }
//
//    public Integer getRank() {
//        return rank;
//    }
//
//    public void setScience(String science){
//        this.science = science;
//    }
//
//    public String getScience(){
//        return science;
//    }
//
//    public void setScienceRank(Integer scienceRank){
//        this.scienceRank = scienceRank;
//    }
//
//    public Integer getScienceRank(){
//        return scienceRank;
//    }

//    @Override
//    public String toString() {
//        return "rank: " + rank + ", science: " + science + ", scienceRank: " + scienceRank;
//
//    }


}
