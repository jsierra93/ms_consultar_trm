package co.com.jsierra.consultartrm.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Route {
    private static final String ROUTE_CONSULT = "/trmhoy";
    private static final String ROUTE_CONSULT_TRM_API = "/trmhoyapi";
    private static final String ROUTE_CONSULT_ID = "/trmfecha/{fecha}";
    private static final String ROUTE_CONSULT_TRM_API_DIA = "/trmdatosgovhoy";
    private static final String ROUTE_CONSULT_TRM_API_HISTORICO = "/trmhistorico";
    private static final String ROUTE_CONSULT_LOCAL = "/trmlocal";

    private static final String ROUTE_CONSULT_TODAY_BD = "/consult/today";
    private static final String ROUTE_CONSULT_HISTORY_BD = "/consult/history";
    private static final String ROUTE_RESET_HISTORY_BD = "/reset/history";
    private static final String ROUTE_SYNC_HISTORY_BD_API = "/sync/history";
    private static final String ROUTE_LOAD_MANUAL_BD = "/load/manual";

    @Bean
    RouterFunction<ServerResponse> routesDemo(Handler handler) {
        return route(GET(ROUTE_CONSULT).and(accept(MediaType.APPLICATION_JSON)), handler::trmHoy)
                .andRoute(GET(ROUTE_CONSULT_LOCAL).and(accept(MediaType.APPLICATION_JSON)), handler::consultaBdLocal)
                .andRoute(GET(ROUTE_CONSULT_ID).and(accept(MediaType.APPLICATION_JSON)), handler::trmFecha)
                .andRoute(GET(ROUTE_CONSULT_TRM_API).and(accept(MediaType.APPLICATION_JSON)), handler::obtenerTrmApi)
                .andRoute(GET(ROUTE_CONSULT_TRM_API_DIA).and(accept(MediaType.APPLICATION_JSON)), handler::obtenerTrmDatosAbiertosHoy)
                .andRoute(GET(ROUTE_CONSULT_TRM_API_HISTORICO).and(accept(MediaType.APPLICATION_JSON)), handler::obtenerTrmApiDatosAbiertos);
    }

    @Bean
    RouterFunction<ServerResponse> routesApi(HandlerApi handlerApi) {
        return route(GET(ROUTE_CONSULT_TODAY_BD).and(accept(MediaType.APPLICATION_JSON)), handlerApi::consultToday)
                .andRoute(GET(ROUTE_CONSULT_HISTORY_BD).and(accept(MediaType.APPLICATION_JSON)), handlerApi::consultHistory);
    }
}
