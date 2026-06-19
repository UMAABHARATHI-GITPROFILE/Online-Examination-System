package com.onlineexam.service;

import java.util.List;

import com.onlineexam.dao.QuestionDAO;
import com.onlineexam.model.Question;

public class QuestionService {

    private QuestionDAO questionDAO = new QuestionDAO();
    public void addQuestion(Question question) {
        questionDAO.addQuestion(question);
    }

    // Calls DAO to get questions
    public List<Question> getQuestionsByExamId(int examId) {
        return questionDAO.getQuestionsByExamId(examId);
    }
}
