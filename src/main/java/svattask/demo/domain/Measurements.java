package svattask.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Measurements {

    @Id
    @Column
    private Long id;

    @Column
    private String dataStructure;

    @Column
    private String operation;

    @Column
    private Long first;

    @Column
    private Long middle;

    @Column
    private Long last;

    @Column
    private Timestamp timeStamp;

}
