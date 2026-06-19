package com.onlineexam.service;

import com.onlineexam.dao.StudentDAO;
import com.onlineexam.model.Student;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    public boolean register(Student student) {
        return studentDAO.registerStudent(student);
    }

    public Student login(String email, String password) {
        return studentDAO.loginStudent(email, password);
    }
}
