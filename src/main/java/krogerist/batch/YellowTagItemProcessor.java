package krogerist.batch;

import krogerist.domain.YellowTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class YellowTagItemProcessor implements ItemProcessor<YellowTag, YellowTag> {

    private static final Logger log = LoggerFactory.getLogger(YellowTagItemProcessor.class);

    @Override
    public YellowTag process(final YellowTag yellowTag) throws Exception {


       // log.info("Converting (" + yellowTag + ") into (" + yellowTag + ")");

        return yellowTag;
    }
}

