package ifsp.edu.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "ifsp.edu.source.Model")
public class ProjetoLivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLivrosApplication.class, args);
	}

}
