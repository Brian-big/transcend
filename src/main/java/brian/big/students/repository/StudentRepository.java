package brian.big.students.repository;

import brian.big.students.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findTopByOrderByAdmissionNumberDesc();

    Optional<Student> findByAdmissionNumber(int admissionNumber);
}
