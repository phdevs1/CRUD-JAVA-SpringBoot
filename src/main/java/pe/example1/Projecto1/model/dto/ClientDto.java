package pe.example1.Projecto1.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private Date registrationDate;
}
