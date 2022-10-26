package com.example.firstwebapp.controller;

import com.example.firstwebapp.entity.Course;
import com.example.firstwebapp.entity.Student;
import com.example.firstwebapp.repository.CourseRepository;
import com.example.firstwebapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/list")
    public String getAllStudent(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "list";
    }


    @GetMapping("/new")
    public String newPage(Model model) {
        model.addAttribute("student", new Student());

        Iterable<Course> courses = courseRepository.findAll();
        model.addAttribute("allCourses", courses);
        return "add";
    }

    @PostMapping("/save")
    public String addStudent(@Valid Student student, BindingResult br, Model model) {
        if (br.hasErrors()) {
            Iterable<Course> courses = courseRepository.findAll();
            model.addAttribute("allCourses", courses);
            return "add";
        }
        studentRepository.save(student);
        return "redirect:list";
    }

    @GetMapping("/update")
    public String goToUpdate(Model model, @RequestParam Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            model.addAttribute("student", student);
        }
        Iterable<Course> courses = courseRepository.findAll();
        model.addAttribute("allCourses", courses);
        return "update";
    }

    @GetMapping("/delete")
    public String goToDeleteStudent(Model model, @RequestParam Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            model.addAttribute("student", student);
        }
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(Model model, Student student) {
        studentRepository.delete(student);
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "list";

    }

    @GetMapping("/name")
    public String getStudentById(Model model, @RequestParam String name) {
        List<Student> students = studentRepository.findByName(name);
        model.addAttribute("students", students);
        return "list";
    }

    @GetMapping("/surname/{surname}")
    public String getStudentBySurname(Model model, @PathVariable String surname) {
        List<Student> students = studentRepository.getStudentBySurname(surname);
        model.addAttribute("students", students);
        return "list";

    }
}
