package brianbig.transcend.repository;

import brianbig.transcend.entities.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<Guardian, String> {
}
