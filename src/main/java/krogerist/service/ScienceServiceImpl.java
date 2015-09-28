package krogerist.service;


import krogerist.domain.YellowTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import krogerist.domain.Science;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScienceServiceImpl implements ScienceService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScienceServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Science getScience( ) {
        Science science = new Science();
        List<YellowTag> yellowTags = new ArrayList<>();

        List<YellowTag> results = jdbcTemplate.query("SELECT yellowTagId, rank, customerId, upc, basePrice, promoPrice, itemDescription, imageUrl  FROM YellowTag", new RowMapper<YellowTag>() {
            @Override
            public YellowTag mapRow(ResultSet rs, int row) throws SQLException {
                return new YellowTag(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        });

        for (YellowTag yellowTag : results) {
            //log.info("Found <" + yellowTag + "> in the database.");
            yellowTags.add(yellowTag);
        }

        science.setYellowTags(yellowTags);

        return science;
    }



}
