package krogerist.batch;

import krogerist.domain.BusinessRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class BusinessRuleItemProcessor implements ItemProcessor<BusinessRule, BusinessRule> {

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleItemProcessor.class);

    @Override
    public BusinessRule process(final BusinessRule businessRule) throws Exception {

        BusinessRule transformedBusinessRule = new BusinessRule(businessRule, businessRule.getScience().toUpperCase());

        //log.info("Converting (" + businessRule + ") into (" + transformedBusinessRule + ")");

        return businessRule;
    }
}
