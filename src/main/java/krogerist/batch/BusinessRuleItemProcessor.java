package krogerist.batch;

import krogerist.domain.BusinessRule;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class BusinessRuleItemProcessor implements ItemProcessor<BusinessRule, BusinessRule> {

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleItemProcessor.class);

    @Override
    public BusinessRule process(final BusinessRule businessRule) throws Exception {


       // log.info("Converting (" + businessRule + ") into (" + businessRule + ")");

        return businessRule;
    }
}
