package ar.com.capitalmarkets.cmaetl.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "vBolsaEntityManagerFactory",
		transactionManagerRef = "vBolsaTransactionManager",
		basePackages = "ar.com.capitalmarkets.cmaetl.vbolsa.repository")
@EnableTransactionManagement
public class VBolsaDBConfig {

	@Primary
	@Bean (name="vBolsaEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean vBolsaEntityManagerFactory (final EntityManagerFactoryBuilder builder,
			final @Qualifier("vBolsa") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.persistenceUnit("vBolsa")
				.packages("ar.com.capitalmarkets.cmaetl.vbolsa.entity")
				.build();
	}
	
	@Primary
	@Bean(name="vBolsaTransactionManager")
	public PlatformTransactionManager vBolsaPlatformTransactionManager (@Qualifier("vBolsaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
//	
//	public EntityManager entityManager() {
//		return vBolsa
//	}

}
