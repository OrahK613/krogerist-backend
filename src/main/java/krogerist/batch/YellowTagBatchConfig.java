package krogerist.batch;


import krogerist.domain.YellowTag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class YellowTagBatchConfig {

    private String sqlYellowTag = "INSERT INTO YellowTag (rank, customerId, upc, basePrice, promoPrice, itemDescription, imageUrl) VALUES (:rank, :customerId, :upc, :basePrice, :promoPrice, :itemDescription, :imageUrl)";

   // private String sqlBusinessRule = "iNSERT INTO BusinessRule (rank, science, scienceRank) VALUES (:rank, :science, :scienceRank)";

    @Bean
    public ItemReader<YellowTag> reader() {
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

//    @Bean
//    public ItemReader<BusinessRule> reader1() {
//        FlatFileItemReader<BusinessRule> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("hackathon_rules.csv"));
//        reader.setLinesToSkip(1);
//        reader.setLineMapper(new DefaultLineMapper<BusinessRule>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "rank", "science", "scienceRank"});
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<BusinessRule>() {{
//                setTargetType(BusinessRule.class);
//            }});
//        }});
//        return reader;
//    }

   @Bean
    public ItemProcessor<YellowTag, YellowTag> processor() {
        return new YellowTagItemProcessor();
    }

//    @Bean
//    public ItemProcessor<BusinessRule, BusinessRule> processor1() {
//        return new BusinessRuleItemProcessor();
//    }


    // end::readerwriterprocessor[]

    @Bean
    public ItemWriter<YellowTag> writer(DataSource dataSource) {
        JdbcBatchItemWriter<YellowTag> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sqlYellowTag);
        writer.setDataSource(dataSource);
        return writer;
    }

//    @Bean
//    public ItemWriter<BusinessRule> writer1(DataSource dataSource) {
//        JdbcBatchItemWriter<BusinessRule> writer = new JdbcBatchItemWriter<>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        writer.setSql(sqlBusinessRule);
//        writer.setDataSource(dataSource);
//        return writer;
//    }

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
        return jobs.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }


    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<YellowTag> reader,
                      ItemWriter<YellowTag> writer, ItemProcessor<YellowTag, YellowTag> processor) {
        return stepBuilderFactory.get("step1")
                .<YellowTag, YellowTag> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

//    @Bean
//    public Step step2(StepBuilderFactory stepBuilderFactory, ItemReader<BusinessRule> reader,
//                      ItemWriter<BusinessRule> writer, ItemProcessor<BusinessRule, BusinessRule> processor) {
//        return stepBuilderFactory.get("step2")
//                .<BusinessRule, BusinessRule> chunk(10)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }



    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
