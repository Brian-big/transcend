package brian.big.classes.controller;

import brian.big.classes.repository.FormRepository;
import brian.big.classes.repository.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("classesController")
@RequestMapping("/classes")
public class Controller {
    @Autowired
    private FormRepository formRepo;
    @Autowired
    private StreamRepository streamRepo;
}
