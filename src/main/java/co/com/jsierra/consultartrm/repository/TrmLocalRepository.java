package co.com.jsierra.consultartrm.repository;

import co.com.jsierra.consultartrm.model.TrmLocalModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TrmLocalRepository extends ReactiveMongoRepository<TrmLocalModel, String> {
/*
Se definen metodos de busqueda para futuros usos
 */
    Flux<TrmLocalModel> findByValue(String value);

    Flux<TrmLocalModel> findByCode(String code);

    Flux<TrmLocalModel> findBySince(String since);

    Flux<TrmLocalModel> findByUntil(String until);

    Flux<TrmLocalModel> findBySinceAndUntil(String since, String until);

    Flux<TrmLocalModel> findBySinceOrUntil(String since, String until);

}
