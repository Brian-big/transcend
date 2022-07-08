package brian.big.students.controller;

import brian.big.students.repository.GuardianRepository;
import brian.big.students.repository.PrefectRepository;
import brian.big.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("studentsController")
@RequestMapping("/students")
public class Controller {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private GuardianRepository guardianRepo;
    @Autowired
    private PrefectRepository prefectRepo;
}
