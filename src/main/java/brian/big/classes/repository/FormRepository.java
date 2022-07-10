package brian.big.classes.repository;

import brian.big.classes.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Long> {
    Optional<Form> findByForm(int form);
}
