package brian.big.admissions.controller;

import brian.big.admissions.domain.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("admissionController")
@RequestMapping("/api/admissions")
public class Controller {
    @Autowired
    AdmissionService admissionService;

}
