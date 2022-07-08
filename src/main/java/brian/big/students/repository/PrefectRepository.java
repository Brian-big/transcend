package brian.big.students.repository;

import brian.big.students.models.Prefect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrefectRepository extends JpaRepository<Prefect, Long> {
}
