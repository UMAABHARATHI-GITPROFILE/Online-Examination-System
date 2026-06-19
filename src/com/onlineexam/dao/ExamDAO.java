package com.onlineexam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.onlineexam.model.Exam;
import com.onlineexam.dao.DBConnection;


public class ExamDAO {
	public void addExam(Exam exam) {

	    String sql = "INSERT INTO exam (exam_name, duration) VALUES (?, ?)";

	    try {
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, exam.getExamName());
	        ps.setInt(2, exam.getDuration());

	        ps.executeUpdate();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    // Method to fetch all exams for student
    public List<Exam> getAllExams() {

        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exam";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Exam exam = new Exam();
                exam.setExamId(rs.getInt("exam_id"));
                exam.setExamName(rs.getString("exam_name"));
                exam.setDuration(rs.getInt("duration"));

                exams.add(exam);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exams;
    }
}
