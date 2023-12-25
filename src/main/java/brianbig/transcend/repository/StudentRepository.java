package brianbig.transcend.repository;

import brianbig.transcend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findTopByOrderByAdmissionNumberDesc();

    Optional<Student> findByAdmissionNumber(int admissionNumber);

    @Query("select s from Student s where s.stream.id = ?1")
    List<Student> studentsInStream(String id);

    @Query("select s from Student s where s.stream.form = ?1")
    List<Student> studentsInForm(int form);
}
