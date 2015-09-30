package krogerist.batch;

import krogerist.domain.Favorite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class FavoriteItemProcessor implements ItemProcessor<Favorite, Favorite> {

    private static final Logger log = LoggerFactory.getLogger(BusinessRuleItemProcessor.class);

    @Override
    public Favorite process(final Favorite favorite) throws Exception {


       // log.info("Converting (" + favorite + ") into (" + favorite + ")");

        return favorite;
    }
}