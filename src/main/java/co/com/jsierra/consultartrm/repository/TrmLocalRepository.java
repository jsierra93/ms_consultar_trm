package co.com.jsierra.consultartrm.repository;

import co.com.jsierra.consultartrm.model.TrmLocal;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TrmLocalRepository extends ReactiveMongoRepository<TrmLocal, String> {

}
