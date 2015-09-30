package krogerist.batch;

import krogerist.domain.WeeklyAd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class WeeklyAdItemProcessor implements ItemProcessor<WeeklyAd, WeeklyAd> {

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleItemProcessor.class);

    @Override
    public WeeklyAd process(final WeeklyAd weeklyAd) throws Exception {


       // log.info("Converting (" + weeklyAd + ") into (" + weeklyAd + ")");

        return weeklyAd;
    }
}