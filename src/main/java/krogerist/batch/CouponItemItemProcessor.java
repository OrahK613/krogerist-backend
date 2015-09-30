package krogerist.batch;

import krogerist.domain.CouponItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CouponItemItemProcessor implements ItemProcessor<CouponItem, CouponItem> {

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleItemProcessor.class);

    @Override
    public CouponItem process(final CouponItem couponItem) throws Exception {


       // log.info("Converting (" + couponItem + ") into (" + couponItem + ")");

        return couponItem;
    }
}
