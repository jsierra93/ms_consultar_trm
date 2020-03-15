package co.com.jsierra.consultartrm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Trm {
    @Id
    private String id;
    private String valor;
    private String fecha;
}
