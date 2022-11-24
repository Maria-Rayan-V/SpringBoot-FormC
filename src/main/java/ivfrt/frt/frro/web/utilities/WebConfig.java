package ivfrt.frt.frro.web.utilities;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class WebConfig {
 
	 @Primary
	 @Bean(name = "jdbcFormC")
	 public JdbcTemplate jdbcFormC(@Qualifier("formc") DataSource ds) {
	  return new JdbcTemplate(ds);
	 }
	 @Primary
	 @Bean
	 @Qualifier("formc")
	 @ConfigurationProperties(prefix = "spring.datasource.formc")
	 public DataSource dataSource2() {
	  return  DataSourceBuilder.create().build();
	 }
	
	 
	 
	 @Bean(name = "jdbcEservices")
	 public JdbcTemplate jdbcEservices(@Qualifier("eservice") DataSource ds) {
	  return new JdbcTemplate(ds);
	 }
	
	 @Bean(name = "eservice")
	 @ConfigurationProperties(prefix = "spring.datasource.eservice")
	 public DataSource dataSource1() {
	  return DataSourceBuilder.create().build();
	 }

 
	 
	 @Bean(name = "jdbcViis")
	 public JdbcTemplate jdbcViis(@Qualifier("viis") DataSource ds) {
	  return new JdbcTemplate(ds);
	 }
	
	 @Bean(name = "viis")
	 @ConfigurationProperties(prefix = "spring.datasource.viis")
	 public DataSource dataSource3() {
	  return DataSourceBuilder.create().build();
	 }
 

}