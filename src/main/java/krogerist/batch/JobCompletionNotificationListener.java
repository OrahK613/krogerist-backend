package krogerist.batch;

import krogerist.domain.YellowTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

//            List<YellowTag> rsYellowTag = jdbcTemplate.query("SELECT yellowTagId, rank, customerId, upc, basePrice, promoPrice, itemDescription, imageUrl  FROM YellowTag", new RowMapper<YellowTag>() {
//                @Override
//                public YellowTag mapRow(ResultSet rs, int row) throws SQLException {
//                    return new YellowTag(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
//                }
//            });
//
//            for (YellowTag yellowTag : rsYellowTag) {
//                log.info("Found <" + yellowTag + "> in the database.");
//
//            }

//            List<BusinessRule> rsBusinessRule = jdbcTemplate.query("SELECT businessRuleId, rank, science, scienceRank FROM BusinessRule", new RowMapper<BusinessRule>() {
//                @Override
//                public BusinessRule mapRow(ResultSet rs, int row) throws SQLException {
//                    return new BusinessRule(rs.getInt(2), rs.getString(3), rs.getInt(4));
//                }
//            });
//
//            for (BusinessRule businessRule : rsBusinessRule) {
//                log.info("Found <" + businessRule + "> in the database.");
//            }
        }
    }
}
