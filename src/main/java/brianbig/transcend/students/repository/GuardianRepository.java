package brianbig.transcend.students.repository;

import brianbig.transcend.students.models.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
}