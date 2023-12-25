package brianbig.transcend.service;

import brianbig.transcend.api.request.StudentCreateRequest;
import brianbig.transcend.entities.Student;
import brianbig.transcend.repository.StreamRepository;
import brianbig.transcend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service("studentService")
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final int startAdmNo = 1000;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StreamRepository streamRepository;
    @Autowired
    private AdmissionService admissionService;

    public ResponseEntity<List<Student>> all() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public Optional<Student> admitStudent(StudentCreateRequest request) {
        Student lastStudent = studentRepository.findTopByOrderByAdmissionNumberDesc();
        int lastAdmNo;
        if (lastStudent != null) {
            lastAdmNo = lastStudent.getAdmissionNumber();
        } else lastAdmNo = startAdmNo;
        log.debug("------- last admission Number: {}", lastAdmNo);
        lastAdmNo++;
        log.debug("------- new admission Number: {}", lastAdmNo);

        Optional<Student> studentByAdmNo = studentRepository.
                findByAdmissionNumber(lastAdmNo);
        if (studentByAdmNo.isPresent()) {
            throw new IllegalStateException("admission number exists");
        }
        var optionalStream = streamRepository.findById(request.streamId());

        if (optionalStream.isEmpty()) {
            log.debug("Stream with id: {} could not be found", request.streamId());
            throw new IllegalStateException("Stream with given id does not exist");
        }

        var student = new Student(request.firstName(), request.lastName(), request.dateOfBirth(), lastAdmNo, optionalStream.get());

        student = studentRepository.saveAndFlush(student);

        return Optional.of(student);
    }

    public Student getStudentById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student getStudentByAdmissionNumber(int admNo) {
        Optional<Student> student = studentRepository.findByAdmissionNumber(admNo);
        return student.orElse(null);
    }

    @Transactional
    public Student updateStudent(Student student) {
        if (!Objects.equals(student.getFirstName(), "") &&
                !Objects.equals(student.getLastName(), "") &&
                student.getDateOfBirth() != null &&
                student.getDateOfAdmission() != null
        ) {
            Student studentById = studentRepository.findById(student.getId())
                    .orElseThrow(() -> new IllegalStateException(""));

            if (!Objects.equals(studentById.getFirstName(), student.getFirstName())) {
                studentById.setFirstName(student.getFirstName());
            }
            if (!Objects.equals(studentById.getLastName(), student.getLastName())) {
                studentById.setLastName(student.getLastName());
            }
            if (!Objects.equals(studentById.getDateOfBirth(), student.getDateOfBirth())) {
                studentById.setDateOfBirth(student.getDateOfBirth());
            }
            if (!Objects.equals(studentById.getDateOfAdmission(), student.getDateOfAdmission())) {
                studentById.setDateOfAdmission(student.getDateOfAdmission());
            }

        }
        return student;
    }

    public ResponseEntity<String> delete(String id) {
        ResponseEntity<String> response;
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            response = new ResponseEntity<>("OPERATION: Delete success!", HttpStatus.OK);
        } else response = new ResponseEntity<>("Student with id not found!", HttpStatus.NOT_FOUND);
        return response;
    }

    public ResponseEntity<List<Student>> getStudentsInStream(int streamId) {
        List<Student> students = studentRepository.studentsInStream(streamId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> getStudentsInForm(int form) {
        List<Student> students = studentRepository.studentsInForm(form);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public Student promote(String id) {
        return admissionService.promoteStudent(id);
    }
}
