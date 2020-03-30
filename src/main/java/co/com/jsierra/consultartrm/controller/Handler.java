package co.com.jsierra.consultartrm.controller;

import co.com.jsierra.consultartrm.model.TrmLocalModel;
import co.com.jsierra.consultartrm.repository.TrmLocalRepository;
import co.com.jsierra.consultartrm.service.WebClientDatosGovApi;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static co.com.jsierra.consultartrm.utilities.UtilitiesManager.*;

@AllArgsConstructor
@Component
public class Handler {

    TrmLocalRepository trmLocalRepository;
    WebClientDatosGovApi webClientDatosGovApi;

    public Mono<ServerResponse> consultHistory(ServerRequest request) {
        Flux<TrmLocalModel> trmActual = trmLocalRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trmActual, TrmLocalModel.class);
    }

    public Mono<ServerResponse> consultToday(ServerRequest request) {
        // LocalDate dayToday = LocalDate.of(2020, 03, 25); // para simular fechas
        LocalDate dayToday = LocalDate.now();
        LocalDate daySince = dayToday;
        LocalDate dayUntil = dayToday;

        boolean workingDay = workingDay(dayToday);
        if (!workingDay) {
            daySince = lastWorkingDay(dayToday);
            dayUntil = nextWorkingDay(dayToday);
        }


        Flux<TrmLocalModel> data = trmLocalRepository.findBySinceAndUntil(daySince.toString(), dayUntil.toString())
                .switchIfEmpty(
                        trmLocalRepository.findAll()
                );

        Flux<TrmLocalModel> response = webClientDatosGovApi.getTrmDay(daySince, dayUntil)
                .map(
                        x -> {
                            TrmLocalModel localModel = TrmLocalModel.builder().code(x.getUnidad()).value(x.getValor()).since(x.getVigenciadesde().toLocalDateTime().toLocalDate()).until(x.getVigenciahasta().toLocalDateTime().toLocalDate()).build();
                            return localModel;
                        }
                ).flatMap(
                        trmLocalRepository::save
                );

        response.subscribe( val -> {
            System.out.println("TRM Obtenida :" +val.getValue());
        });

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, TrmLocalModel.class)
                .switchIfEmpty(
                        ServerResponse.notFound().build()
                );
    }

    public Mono<ServerResponse> resetDataBase(ServerRequest request) {
        Mono<Long> count = trmLocalRepository.findAll().count();
        count.subscribe(
                cant -> System.out.println("Registros eliminados:" +cant)
        );
        Mono<Void> delete = trmLocalRepository.deleteAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(delete, Void.class);
    }

    public Mono<ServerResponse> syncDataBaseFromApi(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("BD Sincronizada con API"), String.class);
    }

    public Mono<ServerResponse> loadDataManual(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("Se han cargado N registros"), String.class);
    }

}
