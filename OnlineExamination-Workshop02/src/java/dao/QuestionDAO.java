/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.QuestionDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huudu
 */
public class QuestionDAO {

    public List<QuestionDTO> getQuestionsByExamId(int examId) throws SQLException, ClassNotFoundException {
        List<QuestionDTO> list = new ArrayList<>();
        String sql = "SELECT question_id, exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, created_by "
                + "FROM tblQuestions WHERE exam_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new QuestionDTO(rs.getInt("question_id"), rs.getInt("exam_id"), rs.getString("question_text"),
                            rs.getString("option_a"), rs.getString("option_b"), rs.getString("option_c"), rs.getString("option_d"),
                            rs.getString("correct_option"), rs.getString("created_by")));
                }
            }
        }
        return list;
    }
    
    public boolean addQuestion(QuestionDTO question) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblQuestions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, created_by) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, question.getExamId());
            ps.setString(2, question.getQuestionText());
            ps.setString(3, question.getOptionA());
            ps.setString(4, question.getOptionB());
            ps.setString(5, question.getOptionC());
            ps.setString(6, question.getOptionD());
            ps.setString(7, question.getCorrectOption());
            ps.setString(8, question.getCreatedBy());
            return ps.executeUpdate() > 0;
        }
    }
}
