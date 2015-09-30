package krogerist.batch;

import krogerist.domain.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CouponItemProcessor implements ItemProcessor<Coupon, Coupon> {

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleItemProcessor.class);

    @Override
    public Coupon process(final Coupon coupon) throws Exception {


       // log.info("Converting (" + coupon + ") into (" + coupon + ")");

        return coupon;
    }
}
