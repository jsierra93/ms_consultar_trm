package co.com.jsierra.consultartrm.controller;

import co.com.jsierra.consultartrm.model.Trm;
import co.com.jsierra.consultartrm.repository.TrmRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class Handler {

    private TrmRepository trmRepository;


    public Mono<ServerResponse> consultarTrm(ServerRequest request){
        Flux<Trm> trmActual = trmRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trmActual, Trm.class);
    }
}
