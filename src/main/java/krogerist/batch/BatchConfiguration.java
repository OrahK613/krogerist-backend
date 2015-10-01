package krogerist.batch;

import krogerist.domain.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {
    private String sqlBusinessRule = "INSERT INTO BusinessRule (rank, science, scienceRank) VALUES (:rank, :science, :scienceRank)";
    private String sqlCoupon = "INSERT INTO Coupon (rank,couponOfferId,customerId,couponNum,score,baseUpc,itemDescription,details,couponImage,brand) VALUES (:rank,:couponOfferId,:customerId,:couponNum,:score,:baseUpc,:itemDescription,:details,:couponImage,:brand)";
    private String sqlCouponItem = "INSERT INTO CouponItem (rank,couponOfferId, customerId, couponNum,baseUpc,itemDescription,itemSize,imageUrl,prodRank) VALUES(:rank,:couponOfferId,:customerId,:couponNum,:baseUpc,:itemDescription,:itemSize,:imageUrl,:prodRank)";
    private String sqlFavorite = "INSERT INTO Favorite (customerId,itemNum,rank,itemDescription,imageUrl) VALUES (:customerId,:itemNum,:rank,:itemDescription,:imageUrl)";
    private String sqlWeeklyAd = "INSERT INTO WeeklyAd (customerId,circularItemId,rank,startDate,endDate,itemTitle,imageUrl,priceString,itemDescription) VALUES (:customerId,:circularItemId,:rank,:startDate,:endDate,:itemTitle,:imageUrl,:priceString,:itemDescription)";
    private String sqlWeeklyAdItem = "INSERT INTO WeeklyAdItem (prodCode,circularItemId,adIdRank,itemDescription,imageUrl,itemRank) VALUES (:prodCode,:circularItemId,:adIdRank,:itemDescription,:imageUrl,:itemRank)";
    private String sqlYellowTag = "INSERT INTO YellowTag (rank, customerId, upc, basePrice, promoPrice, itemDescription, imageUrl) VALUES (:rank, :customerId, :upc, :basePrice, :promoPrice, :itemDescription, :imageUrl)";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    // itemReaders
    @Bean
    public ItemReader<BusinessRule> businessRuleReader() {
        FlatFileItemReader<BusinessRule> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hackathon_rules.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<BusinessRule>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "rank", "science", "scienceRank"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<BusinessRule>() {{
                setTargetType(BusinessRule.class);
            }});
        }});
        return reader;
    }
    @Bean
    public ItemReader<Coupon> couponReader() {
        FlatFileItemReader<Coupon> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hackathon_matt_coupons.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Coupon>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "rank","couponOfferId","customerId","couponNum","score","baseUpc","itemDescription","details","couponImage","brand"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Coupon>() {{
                setTargetType(Coupon.class);
            }});
        }});
        return reader;
    }
    @Bean
    public ItemReader<CouponItem> couponItemReader() {
        FlatFileItemReader<CouponItem> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hackathon_matt_coupon_items.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<CouponItem>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "rank","couponOfferId","customerId","couponNum","baseUpc","itemDescription","itemSize","imageUrl","prodRank"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CouponItem>() {{
                setTargetType(CouponItem.class);
            }});
        }});
        return reader;
    }
    @Bean
    public ItemReader<Favorite> favoriteReader() {
        FlatFileItemReader<Favorite> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hackathon_matt_favorites.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Favorite>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "customerId","itemNum","rank","itemDescription","imageUrl"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Favorite>() {{
                setTargetType(Favorite.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemReader<WeeklyAd> weeklyAdReader() {
        FlatFileItemReader<WeeklyAd> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hackathon_matt_weekly_ads.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<WeeklyAd>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "customerId","circularItemId","rank","startDate","endDate","itemTitle","imageUrl","priceString","itemDescription"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<WeeklyAd>() {{
                setTargetType(WeeklyAd.class);
            }});
        }});
        return reader;
    }
    @Bean
    public ItemReader<WeeklyAdItem> weeklyAdItemReader() {
        FlatFileItemReader<WeeklyAdItem> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hackathon_matt_weekly_ads_items.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<WeeklyAdItem>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "prodCode","circularItemId","adIdRank","itemDescription","imageUrl","itemRank"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<WeeklyAdItem>() {{
                setTargetType(WeeklyAdItem.class);
            }});
        }});
        return reader;
    }
    @Bean
    public ItemReader<YellowTag> yellowTagReader() {
        FlatFileItemReader<YellowTag> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hackathon_matt_yellow_tag.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<YellowTag>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "rank", "customerId", "upc", "basePrice", "promoPrice", "itemDescription", "imageUrl" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<YellowTag>() {{
                setTargetType(YellowTag.class);
            }});
        }});
        return reader;
    }

    // IteProcessors
    @Bean
    public ItemProcessor<BusinessRule, BusinessRule> businessRuleProcessor() {
        return new BusinessRuleItemProcessor();
    }
    @Bean
    public ItemProcessor<Coupon, Coupon> couponProcessor() {
        return new CouponItemProcessor();
    }
    @Bean
    public ItemProcessor<CouponItem, CouponItem> couponItemProcessor() {
        return new CouponItemItemProcessor();
    }
    @Bean
    public ItemProcessor<Favorite, Favorite> FavoriteProcessor() {
        return new FavoriteItemProcessor();
    }
    @Bean
    public ItemProcessor<WeeklyAd, WeeklyAd> WeeklyAdProcessor() {
        return new WeeklyAdItemProcessor();
    }
    @Bean
    public ItemProcessor<WeeklyAdItem, WeeklyAdItem> WeeklyAdItemProcessor() {
        return new WeeklyAdItemItemProcessor();
    }
    @Bean
    public ItemProcessor<YellowTag, YellowTag> yellowTagProcessor() {
        return new YellowTagItemProcessor();
    }


    // ItemWriters
    @Bean
    public ItemWriter<BusinessRule> businessRuleWriter(DataSource dataSource) {
        JdbcBatchItemWriter<BusinessRule> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlBusinessRule);
        writer.setDataSource(dataSource);
        return writer;
    }
    @Bean
    public ItemWriter<Coupon> couponWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Coupon> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlCoupon);
        writer.setDataSource(dataSource);
        return writer;
    }
    @Bean
    public ItemWriter<CouponItem> couponItemWriter(DataSource dataSource) {
        JdbcBatchItemWriter<CouponItem> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlCouponItem);
        writer.setDataSource(dataSource);
        return writer;
    }
    @Bean
    public ItemWriter<Favorite> FavoriteItemWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Favorite> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlFavorite);
        writer.setDataSource(dataSource);
        return writer;
    }
    @Bean
    public ItemWriter<WeeklyAd> WeeklyAdItemWriter(DataSource dataSource) {
        JdbcBatchItemWriter<WeeklyAd> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlWeeklyAd);
        writer.setDataSource(dataSource);
        return writer;
    }
    @Bean
    public ItemWriter<WeeklyAdItem> WeeklyAdItemItemWriter(DataSource dataSource) {
        JdbcBatchItemWriter<WeeklyAdItem> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlWeeklyAdItem);
        writer.setDataSource(dataSource);
        return writer;
    }
    @Bean
    public ItemWriter<YellowTag> yellowTagWriter(DataSource dataSource) {
        JdbcBatchItemWriter<YellowTag> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlYellowTag);
        writer.setDataSource(dataSource);
        return writer;
    }



    // Steps
    @Bean
    public Step loadBusinessRules(ItemReader<BusinessRule> reader, ItemWriter<BusinessRule> writer, ItemProcessor<BusinessRule, BusinessRule> processor) {
        return stepBuilderFactory.get("loadBusinessRules")
                .<BusinessRule, BusinessRule> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public Step loadCoupons(ItemReader<Coupon> reader, ItemWriter<Coupon> writer, ItemProcessor<Coupon, Coupon> processor) {
        return stepBuilderFactory.get("loadCoupons")
                .<Coupon, Coupon> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public Step loadCouponItems(ItemReader<CouponItem> reader, ItemWriter<CouponItem> writer, ItemProcessor<CouponItem, CouponItem> processor) {
        return stepBuilderFactory.get("loadCouponItems")
                .<CouponItem, CouponItem> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public Step loadFavorites(ItemReader<Favorite> reader, ItemWriter<Favorite> writer, ItemProcessor<Favorite, Favorite> processor) {
        return stepBuilderFactory.get("loadFavorites")
                .<Favorite, Favorite> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public Step loadWeeklyAds(ItemReader<WeeklyAd> reader, ItemWriter<WeeklyAd> writer, ItemProcessor<WeeklyAd, WeeklyAd> processor) {
        return stepBuilderFactory.get("loadWeeklyAds")
                .<WeeklyAd, WeeklyAd> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public Step loadWeeklyAdItems(ItemReader<WeeklyAdItem> reader, ItemWriter<WeeklyAdItem> writer, ItemProcessor<WeeklyAdItem, WeeklyAdItem> processor) {
        return stepBuilderFactory.get("loadWeeklyAdItems")
                .<WeeklyAdItem, WeeklyAdItem> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public Step loadYellowTags(ItemReader<YellowTag> reader, ItemWriter<YellowTag> writer, ItemProcessor<YellowTag, YellowTag> processor) {
        return stepBuilderFactory.get("loadYellowTags")
                .<YellowTag, YellowTag> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // Job
    @Qualifier("loadSciencesJob")
    @Bean
    public Job loadSciencesJob(@Qualifier("loadBusinessRules")Step loadBusinessRules,
                               @Qualifier("loadCoupons")Step loadCoupons,
                               @Qualifier("loadCouponItems")Step loadCouponItems,
                               @Qualifier("loadFavorites")Step loadFavorites,
                               @Qualifier("loadWeeklyAds")Step loadWeeklyAds,
                               @Qualifier("loadWeeklyAdItems")Step loadWeeklyAdItems,
                               @Qualifier("loadYellowTags") Step loadYellowTags)
    {

        return jobBuilderFactory.get("loadSciencesJob")
                .incrementer(new RunIdIncrementer())
                .flow(loadBusinessRules)
                .next(loadCoupons)
                .next(loadCouponItems)
                .next(loadFavorites)
                .next(loadWeeklyAds)
                .next(loadWeeklyAdItems)
                .next(loadYellowTags)
                .end().build();
    }

    // Data Source
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}