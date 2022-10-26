package com.example.firstwebapp.repository;

import com.example.firstwebapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByName(String name);

    @Query(value = "select * from student where Surname = ?1", nativeQuery = true)
    List<Student> getStudentBySurname(String surname);
}
