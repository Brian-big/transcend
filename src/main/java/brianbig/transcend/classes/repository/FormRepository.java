package brianbig.transcend.classes.repository;

import brianbig.transcend.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
    Form findByForm(int form);
}
