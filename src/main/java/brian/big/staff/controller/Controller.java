package brian.big.staff.controller;

import brian.big.staff.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("staffController")
@RequestMapping("/staff")
public class Controller {
    @Autowired
    private TeacherRepository teacherRepo;

}
