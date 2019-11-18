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
		entityManagerFactoryRef = "vFondosEntityManagerFactory",
		transactionManagerRef = "vFondosTransactionManager",
		basePackages = "ar.com.capitalmarkets.cmaetl.vfondos.repository")
@EnableTransactionManagement
public class VFondosDBConfig {
	
	@Bean (name="vFondosEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean vFondosEntityManagerFactory (final EntityManagerFactoryBuilder builder,
			final @Qualifier("vFondos") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.persistenceUnit("vFondos")
				.packages("ar.com.capitalmarkets.cmaetl.vfondos.entity")
				.build();
	}

	@Primary
	@Bean(name="vFondosTransactionManager")
	public PlatformTransactionManager vBolsaPlatformTransactionManager (@Qualifier("vFondosEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
