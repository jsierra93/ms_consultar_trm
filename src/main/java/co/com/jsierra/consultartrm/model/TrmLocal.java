package co.com.jsierra.consultartrm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Historico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrmLocal {

    @Id
    private String id;
    private String valor;
    private String unidad;
    private String vigenciadesde;
    private String vigenciahasta;
}
