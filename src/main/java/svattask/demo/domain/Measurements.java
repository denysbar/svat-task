package svattask.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Measurements {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String dataStructure;

    private String operation;

    private Long first;

    private Long middle;

    private Long last;

    private Timestamp timestamp;

}
