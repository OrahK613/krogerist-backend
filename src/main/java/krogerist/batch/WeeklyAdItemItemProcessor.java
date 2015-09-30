package krogerist.batch;

import krogerist.domain.WeeklyAdItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class WeeklyAdItemItemProcessor implements ItemProcessor<WeeklyAdItem, WeeklyAdItem> {

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleItemProcessor.class);

    @Override
    public WeeklyAdItem process(final WeeklyAdItem weeklyAdItem) throws Exception {


       // log.info("Converting (" + weeklyAdItem + ") into (" + weeklyAdItem + ")");

        return weeklyAdItem;
    }
}
