package svattask.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MeasurementsRepository extends JpaRepository<MeasurementsEntity, Long> {

}
