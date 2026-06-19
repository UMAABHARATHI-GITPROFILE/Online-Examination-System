package com.onlineexam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.onlineexam.model.Question;

public class QuestionDAO {
	public void addQuestion(Question q) {

	    String sql = "INSERT INTO question (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try {
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, q.getExamId());
	        ps.setString(2, q.getQuestionText());
	        ps.setString(3, q.getOptionA());
	        ps.setString(4, q.getOptionB());
	        ps.setString(5, q.getOptionC());
	        ps.setString(6, q.getOptionD());
	        ps.setString(7, String.valueOf(q.getCorrectOption()));

	        ps.executeUpdate();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


    // Fetch questions for a selected exam
    public List<Question> getQuestionsByExamId(int examId) {

        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE exam_id = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, examId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question q = new Question();
                q.setQuestionId(rs.getInt("question_id"));
                q.setExamId(rs.getInt("exam_id"));
                q.setQuestionText(rs.getString("question_text"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectOption(rs.getString("correct_option").charAt(0));

                questions.add(q);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }
}
