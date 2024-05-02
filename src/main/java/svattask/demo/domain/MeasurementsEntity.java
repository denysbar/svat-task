package svattask.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity(name="measurements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "list_type")
    private String listType;

    private String method;

    private Long start;

    private Long middle;

    private Long ending;

    private Timestamp timestamp;

}
