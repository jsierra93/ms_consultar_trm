package co.com.jsierra.consultartrm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("Historico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrmLocalModel {

    @Id
    private String id;
    private String value;
    private String code;
    private LocalDate since;
    private LocalDate until;
}
