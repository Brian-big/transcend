package brian.big.students.repository;

import brian.big.students.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findTopByOrderByIdDesc();
}
