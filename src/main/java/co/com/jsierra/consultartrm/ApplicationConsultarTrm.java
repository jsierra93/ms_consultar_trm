package co.com.jsierra.consultartrm;

import co.com.jsierra.consultartrm.model.Trm;
import co.com.jsierra.consultartrm.repository.TrmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.text.DateFormat;
import java.util.Date;

@SpringBootApplication
public class ApplicationConsultarTrm {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsultarTrm.class, args);
    }

    @Bean
    CommandLineRunner init(TrmRepository repository){
        return args -> {

            Flux<Trm> trm = Flux.just(
                    Trm.builder().fecha("03/15/2020").valor("3941.920").build()
            ).flatMap(p-> repository.save(p));

            trm.thenMany(repository.findAll())
                    .subscribe(System.out::println);

        };
    }
}
