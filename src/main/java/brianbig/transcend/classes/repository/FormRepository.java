package brianbig.transcend.classes.repository;

import brianbig.transcend.classes.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
    Form findByForm(int form);
}
