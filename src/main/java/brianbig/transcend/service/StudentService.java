package brianbig.transcend.service;

import brianbig.transcend.api.request.StudentCreateRequest;
import brianbig.transcend.api.request.StudentUpdateRequest;
import brianbig.transcend.entities.Student;
import brianbig.transcend.repository.StreamRepository;
import brianbig.transcend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service("studentService")
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final int startAdmNo = 1000;


    private final StudentRepository studentRepository;

    private final StreamRepository streamRepository;

    private final AdmissionService admissionService;

    @Autowired
    public StudentService(StudentRepository studentRepository, StreamRepository streamRepository, AdmissionService admissionService) {
        this.studentRepository = studentRepository;
        this.streamRepository = streamRepository;
        this.admissionService = admissionService;
    }

    public List<Student> all() {
        return studentRepository.findAll();
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

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByAdmissionNumber(int admNo) {
        return studentRepository.findByAdmissionNumber(admNo);
    }

    @Transactional
    public Optional<Student> updateStudent(StudentUpdateRequest request) {

        var optionalStudent = studentRepository.findById(request.id());
        if (optionalStudent.isEmpty()) {
            throw new IllegalStateException("Could not find a student with given ID");
        }

        final var student = optionalStudent.get();

        if (!Objects.equals(student.getFirstName(), request.firstName())) {
            optionalStudent.get().setFirstName(request.firstName());
        }
        if (!Objects.equals(student.getLastName(), request.lastName())) {
            optionalStudent.get().setLastName(request.lastName());
        }
        if (!Objects.equals(optionalStudent.get().getDateOfBirth(), request.dateOfBirth())) {
            optionalStudent.get().setDateOfBirth(request.dateOfBirth());
        }
        if (!Objects.equals(optionalStudent.get().getDateOfAdmission(), request.dateOfAdmission())) {
            optionalStudent.get().setDateOfAdmission(request.dateOfAdmission());
        }

        optionalStudent.ifPresent(studentRepository::saveAndFlush);

        return optionalStudent;


    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsInStream(String streamId) {
        return studentRepository.studentsInStream(streamId);
    }

    public List<Student> getStudentsInForm(int form) {
        return studentRepository.studentsInForm(form);
    }

    public Optional<Student> promote(String id) {
        return admissionService.promoteStudent(id);
    }
}
