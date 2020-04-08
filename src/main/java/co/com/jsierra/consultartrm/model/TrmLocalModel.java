package co.com.jsierra.consultartrm.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Historico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TrmLocalModel {

    @Id
    private String id;
    private String value;
    private String code;
    private String since;
    private String until;
}
