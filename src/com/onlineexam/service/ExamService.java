package com.onlineexam.service;

import java.util.List;

import com.onlineexam.dao.ExamDAO;
import com.onlineexam.model.Exam;

public class ExamService {

    private ExamDAO examDAO = new ExamDAO();
    public void addExam(Exam exam) {
        examDAO.addExam(exam);
    }

    // Method to get all exams for students
    public List<Exam> getAllExams() {
        return examDAO.getAllExams();
    }
}
