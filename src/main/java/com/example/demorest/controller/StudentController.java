package com.example.demorest.controller;

import com.example.demorest.domain.Student;
import com.example.demorest.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //  @ResponseBody  // no need cause of restcontroller
    @GetMapping(value = "/home",produces = MediaType.TEXT_PLAIN_VALUE)
    public String welcome(){ // no need ResponseEntity to return
     //   return new ResponseEntity("welcome",HttpStatus.OK);
        return "Welcome Spring MVC REST";
    }

    @GetMapping("/creation")
    public String create(){
        Arrays.asList(new Student("Mg Mg",20,"Latha"),
                      new Student("Aung Aung",21,"Ygn"),
                      new Student("Ma Ma",22,"Mdy"))
                      .forEach(studentRepository::save);
        return "successfully created";
    }

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> showAllStudents(){
        return this.studentRepository.findAll();
    }

    @GetMapping(value = "/all/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudentById(@PathVariable int id){
        return this.studentRepository.getOne(id);
    }
}
