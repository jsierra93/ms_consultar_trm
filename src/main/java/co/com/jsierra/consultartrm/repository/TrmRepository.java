package co.com.jsierra.consultartrm.repository;

import co.com.jsierra.consultartrm.model.Trm;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TrmRepository extends ReactiveMongoRepository<Trm, String> {
}
