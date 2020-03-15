package co.com.jsierra.consultartrm.controller;

import co.com.jsierra.consultartrm.model.Trm;
import co.com.jsierra.consultartrm.repository.TrmRepository;
import com.sun.jna.platform.win32.WinCrypt;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@AllArgsConstructor
@Component
public class Handler {

    TrmRepository trmRepository;


    public Mono<ServerResponse> trmHoy(ServerRequest request){
        Calendar fecha = new GregorianCalendar();

       String fechaActual = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH) +"/"+ fecha.get(Calendar.MONTH) +"/"+ fecha.get(Calendar.YEAR));
        System.out.println(fechaActual);
        Flux<Trm> trmActual = trmRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trmActual, Trm.class);
    }

    public Mono<ServerResponse> trmFecha(ServerRequest request){
        Mono<Trm> trmActual = trmRepository.findById(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trmActual, Trm.class);
    }
}
