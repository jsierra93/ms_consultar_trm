package co.com.jsierra.consultartrm;

import co.com.jsierra.consultartrm.model.Trm;
import co.com.jsierra.consultartrm.repository.TrmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@SpringBootApplication
public class ApplicationConsultarTrm {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsultarTrm.class, args);
    }

  /*  @Bean
    CommandLineRunner init(TrmRepository repository) {
        return args -> {

            Flux<Trm> trm = Flux.just(
                    Trm.builder().fecha(LocalDate.parse("2020-03-14")).valor("3941.920").build(),
                    Trm.builder().fecha(LocalDate.parse("2020-03-13")).valor("4041.920").build()
            ).flatMap(p -> repository.save(p));

            trm.thenMany(repository.findAll())
                    .subscribe(System.out::println);

        };
    }*/
}
