package co.com.jsierra.consultartrm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrmApiModel {

    private String valor;
    private String unidad;
    private Timestamp vigenciadesde;
    private Timestamp vigenciahasta;
}


