package krogerist.domain;

/**
 * Created by dcpdev on 9/27/15.
 */
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

    public YellowTag(YellowTag yellowTag, String itemDescription)
    {
        yellowTag.setItemDescription(itemDescription);
    }


    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRank() {
        return rank;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setUpc(String upc){
        this.upc = upc;
    }

    public String getUpc(){
        return upc;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getBasePrice(){
        return basePrice;
    }

    public void setPromoPrice(String promoPrice) {
        this.promoPrice = promoPrice;
    }

    public String getPromoPrice(){
        return promoPrice;
    }

    public void setItemDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }

    public String getItemDescription(){
        return itemDescription;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    @Override
    public String toString() {
        return "rank: " + rank + ", customerId: " + customerId + ", upc: " + upc + ", basePrice: " + basePrice +
                ", promoPrice: " + promoPrice + ", itemDescription: " + itemDescription + ", imageUrl: " + imageUrl;
    }

}
