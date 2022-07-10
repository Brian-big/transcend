package brian.big.students.repository;

import brian.big.students.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findTopByOrderByAdmissionNumberDesc();

    Optional<Student> findByAdmissionNumber(int admissionNumber);

    @Query("select s from Student s where s.stream.id = ?1")
    List<Student> studentsInStream(long id);
}
