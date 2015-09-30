package krogerist.service;

import krogerist.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import krogerist.domain.Science;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScienceServiceImpl implements ScienceService {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(ScienceServiceImpl.class);

    @Autowired
    public ScienceServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getScience( ) {

        // Get the list of BusinessRule items
        List<BusinessRule> businessRules = new ArrayList<>();

        List<BusinessRule> businessRuleResults = jdbcTemplate.query("SELECT businessRuleId, rank, science, scienceRank FROM BusinessRule", new RowMapper<BusinessRule>() {
            @Override
            public BusinessRule mapRow(ResultSet rs, int row) throws SQLException {
                return new BusinessRule(rs.getInt(2), rs.getString(3), rs.getInt(4));
            }
        });

        for (BusinessRule businessRule : businessRuleResults) {
            businessRules.add(businessRule);
        }


        // Get the list of Coupon items
        List<Coupon> coupons = new ArrayList<>();

        List<Coupon> couponResults = jdbcTemplate.query("SELECT couponId, rank, couponOfferId, customerId, couponNum, score, baseUpc, itemDescription, details,couponImage,brand FROM Coupon", new RowMapper<Coupon>() {
            @Override
            public Coupon mapRow(ResultSet rs, int row) throws SQLException {
                return new Coupon(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
            }
        });

        for (Coupon coupon : couponResults) {
            coupons.add(coupon);
        }

        // Get the list of CouponItem items
        List<CouponItem> couponItems = new ArrayList<>();

        List<CouponItem> couponItemResults = jdbcTemplate.query("SELECT couponItemId, rank, couponOfferId, customerId, couponNum, baseUpc, itemDescription, itemSize,imageUrl,prodRank FROM CouponItem", new RowMapper<CouponItem>() {
            @Override
            public CouponItem mapRow(ResultSet rs, int row) throws SQLException {
                return new CouponItem(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
            }
        });

        for (CouponItem couponItem : couponItemResults) {
            couponItems.add(couponItem);
        }


        // Get the list of Favorite items
        List<Favorite> favorites = new ArrayList<>();

        List<Favorite> favoriteResults = jdbcTemplate.query("SELECT favoriteId, customerId, itemNum, rank, itemDescription, imageUrl  FROM Favorite", new RowMapper<Favorite>() {
            @Override
            public Favorite mapRow(ResultSet rs, int row) throws SQLException {
                return new Favorite(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
        });

        for (Favorite favorite : favoriteResults) {
            favorites.add(favorite);
        }


        // Get the list of WeeklyAd items
        List<WeeklyAd> weeklyAds = new ArrayList<>();

        List<WeeklyAd> weeklyAdsResults = jdbcTemplate.query("SELECT weeklyAdId, customerId, circularItemId, rank, startDate, endDate, itemTitle  FROM WeeklyAd", new RowMapper<WeeklyAd>() {
            @Override
            public WeeklyAd mapRow(ResultSet rs, int row) throws SQLException {
                return new WeeklyAd(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        });

        for (WeeklyAd weeklyAd : weeklyAdsResults) {
            weeklyAds.add(weeklyAd);
        }


        // Get the list of WeeklyAdItem items
        List<WeeklyAdItem> weeklyAdItems = new ArrayList<>();

        List<WeeklyAdItem> weeklyAdItemResults = jdbcTemplate.query("SELECT weeklyAdItemId, prodCode, circularItemId, adIdRank, itemDescription, imageUrl, itemRank  FROM WeeklyAdItem", new RowMapper<WeeklyAdItem>() {
            @Override
            public WeeklyAdItem mapRow(ResultSet rs, int row) throws SQLException {
                return new WeeklyAdItem(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }
        });

        for (WeeklyAdItem weeklyAdItem : weeklyAdItemResults) {
            weeklyAdItems.add(weeklyAdItem);
        }


        // Get the list of YellowTag items
        List<YellowTag> yellowTags = new ArrayList<>();

        List<YellowTag> yellowTagResults = jdbcTemplate.query("SELECT yellowTagId, rank, customerId, upc, basePrice, promoPrice, itemDescription, imageUrl  FROM YellowTag", new RowMapper<YellowTag>() {
            @Override
            public YellowTag mapRow(ResultSet rs, int row) throws SQLException {
                return new YellowTag(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        });

        for (YellowTag yellowTag : yellowTagResults) {
            yellowTags.add(yellowTag);
        }



        List<Map<String, Object>> science = new ArrayList<>();
        for (BusinessRule businessRule : businessRules)
        {
            switch(businessRule.getScience()) {

                case "Coupons Adj": {
                    Map<String, Object> couponMap = new HashMap<>();
                    Coupon coupon = coupons.get(0);
                    couponMap.put("tar_cou_ofr_id", coupon.getCouponOfferId());
                    couponMap.put("dib_cust_id", coupon.getCustomerId());
                    couponMap.put("coupon_num", coupon.getCouponNum());
                    couponMap.put("score", coupon.getScore());
                    couponMap.put("base_upc_num", coupon.getBaseUpc());
                    couponMap.put("description", coupon.getItemDescription());
                    couponMap.put("details", coupon.getDetails());
                    couponMap.put("coupon_image", coupon.getCouponImage());
                    couponMap.put("brand", coupon.getBrand());
                    couponMap.put("id", coupon.getRank() + "-" + coupon.getCouponOfferId());
                    couponMap.put("type", "coupon");
                    couponMap.put("image_url", "Need to get this image");
                    if(coupons.size() > 1)
                    {
                        coupons.remove(0);
                    }
                    science.add(couponMap);
                    break;
                }
                case "Favorites": {
                    Map<String, Object> favoriteMap = new HashMap<>();
                    Favorite favorite = favorites.get(0);
                    favoriteMap.put("dib_cust_id", favorite.getCustomerId());
                    favoriteMap.put("item_no", favorite.getItemNum());
                    favoriteMap.put("rank", favorite.getRank());
                    favoriteMap.put("item_description", favorite.getItemDescription());
                    favoriteMap.put("image_url", favorite.getImageUrl());
                    favoriteMap.put("id", favorite.getRank() + "-" + favorite.getItemNum());
                    favoriteMap.put("type", "favorite");
                    if(favorites.size() > 1)
                    {
                        favorites.remove(0);
                    }
                    science.add(favoriteMap);
                    break;
                }
               case "Weekly Ads": {
//                   "CategoryId": 9,
//                           "CategoryName": "Beverages",
//                           "Description": "Select Varieties, 12 pk, 12 fl oz Cans or 8 pk, 12 fl oz Bottles",
//                           "EndDate": "2015-09-29",
//                           "wcsProductId": 90423200,
//                           "ImageFile": "https://f.wishabi.net/page_pdf_images/1785874/0d3f5cfa-55de-11e5-82d6-22000bb68123/x_large",
//                           "PriceString": "When You Buy FINAL COST 3 for $10 With Card",
//                           "StartDate": "2015-09-16",
//                           "ThumbnailFile": "https://f.wishabi.net/page_pdf_images/1785874/0d3f5cfa-55de-11e5-82d6-22000bb68123/large",
//                           "Title": "Coca-Cola, Pepsi or 7UP",
//                           "type": "weeklyad",
//                           "rank": 8,
//                           "id": "8-90423200"

                    Map<String, Object> weeklyAdMap = new HashMap<>();
                    WeeklyAd weeklyAd = weeklyAds.get(0);
                    weeklyAdMap.put("CategoryId", "42");
                    weeklyAdMap.put("CategoryName", "Snacks");
                    weeklyAdMap.put("Description","BLANK BLANK BLANK");
                    weeklyAdMap.put("EndDate", weeklyAd.getEndDate());
                    weeklyAdMap.put("wcsProductId", weeklyAd.getCircularItemId());
                    weeklyAdMap.put("ImageFile", "We need images!");
                    weeklyAdMap.put("PriceString", "It's all free(for real)");
                    weeklyAdMap.put("StartDate", weeklyAd.getStartDate());
                   weeklyAdMap.put("ThumbnailFile", "No thumbs :(");
                   weeklyAdMap.put("Title", weeklyAd.getItemTitle());
                    weeklyAdMap.put("id", weeklyAd.getRank() + "-" + weeklyAd.getItemNum());
                    weeklyAdMap.put("type", "weeklyad");
                   weeklyAdMap.put("rank", weeklyAd.getRank());
                   if(weeklyAds.size() > 1)
                   {
                       weeklyAds.remove(0);
                   }

                    science.add(weeklyAdMap);
                 }
                case "Yellow Tag":
                {
                    Map<String, Object> yellowTagMap = new HashMap<>();
                    YellowTag yellowTag = yellowTags.get(0);
                    yellowTagMap.put("rank", yellowTag.getRank());
                    yellowTagMap.put("dib_cust_id", yellowTag.getCustomerId());
                    yellowTagMap.put("base_upc_num", yellowTag.getUpc());
                    yellowTagMap.put("base_price", yellowTag.getBasePrice());
                    yellowTagMap.put("item_description", yellowTag.getItemDescription());
                    yellowTagMap.put("image_url", yellowTag.getImageUrl());
                    yellowTagMap.put("id", yellowTag.getRank() + "-" + yellowTag.getUpc());
                    yellowTagMap.put("type", "yellowtag");
                    if(yellowTag.getRank() == 1)
                    {
                        yellowTagMap.put("superDeal", true);
                    }
                    else
                    {
                        yellowTagMap.put("superDeal", false);
                    }

                    if(yellowTags.size() > 1)
                    {
                        yellowTags.remove(0);
                    }
                    science.add(yellowTagMap);
                    break;
                }
                default:
                {
                    Map<String, Object> emptyMap = new HashMap<>();
                    science.add(emptyMap);
                    break;
                }


            }
        }

        return science;
    }



}
