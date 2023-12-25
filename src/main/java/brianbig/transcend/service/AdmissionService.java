package brianbig.transcend.service;

import brianbig.transcend.entities.Stream;
import brianbig.transcend.entities.Student;
import brianbig.transcend.entities.enums.ClassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdmissionService {


    private final StudentService studentService;
    private final ClassesService classesService;

    @Autowired
    @Lazy
    public AdmissionService(StudentService studentService, ClassesService classesService) {
        this.studentService = studentService;
        this.classesService = classesService;
    }

    @Transactional
    public Student promoteStudent(String id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            System.out.println("Student with given admission number does not exist");
            return null;
        }

        Stream currentStream = student.getStream();
        if (currentStream == null)
            return null;
        Stream nextStream = handleStreamPromotion(currentStream);

        student.setStream(nextStream);
        Student student1 = studentService.updateStudent(student);
        if (student1 == null) {
            System.out.println("Promote student to " + student.getStream() + "fail");
            return null;
        }
        return student1;
    }

    private Stream handleStreamPromotion(Stream currentStream) {
        ClassForm nextForm = getNextForm(currentStream.getForm());
        Optional<Stream> streamNext;
        if (nextForm == null)
            return null;

        streamNext = classesService.getStream(nextForm.name(), currentStream.getName());
        if (streamNext.isPresent()) {
            return streamNext.get();
        } else {
            Stream promotedStream = new Stream(nextForm, currentStream.getName());
            Stream insertedStream = classesService.addStream(promotedStream);
            if (insertedStream == null) {
                System.out.println("New stream creation failed");
                return null;
            }
            return insertedStream;
        }

    }

    private ClassForm getNextForm(ClassForm currentForm) {
        ClassForm newForm;
        int currForm = ClassForm.form(currentForm);
        ClassForm formNext;
        if (currForm >= 4) {
            return null;
        } else {
            currForm++;
            newForm = ClassForm.from(currForm);
        }

        return newForm;

    }


}
