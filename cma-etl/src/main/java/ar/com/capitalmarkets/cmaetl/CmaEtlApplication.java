package ar.com.capitalmarkets.cmaetl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.com.capitalmarkets.cmaetl.service.Proceso;

@SpringBootApplication
public class CmaEtlApplication implements CommandLineRunner {

//	private static final Logger logger = LoggerFactory.getLogger(CmaEtlApplication.class);
	@Autowired private Proceso procesoBean;
	
	public static void main(String[] args) {
		SpringApplication.run(CmaEtlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		procesoBean.procesarComitentes();

	}

}
