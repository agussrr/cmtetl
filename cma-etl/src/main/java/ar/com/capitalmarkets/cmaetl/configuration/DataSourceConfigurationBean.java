package ar.com.capitalmarkets.cmaetl.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfigurationBean {
	
	@Bean (name="vBolsa")
	@Primary
	@ConfigurationProperties("app.datasource.vb.configuration")
	public HikariDataSource vBolsaHikariDataSource() {
		return vBolsaDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Bean 
	@Primary
	@ConfigurationProperties("app.datasource.vb")
	public DataSourceProperties vBolsaDataSourceProperties() {
		return new DataSourceProperties();
	}
	 
	@Bean (name="vFondos")
	@ConfigurationProperties("app.datasource.vf.configuration")
	public HikariDataSource vFondosHikariDataSource() {
		return vFondosDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Bean 
	@ConfigurationProperties("app.datasource.vf")
	public DataSourceProperties vFondosDataSourceProperties() {
		return new DataSourceProperties();
	}
	
//	@Bean (name="vFondos")
//	@ConfigurationProperties("spring.vf.datasource")
//	public DataSource vFondosDataSource() {
//		return DataSourceBuilder.create().build();
//	}

}

