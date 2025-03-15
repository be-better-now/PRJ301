/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huudu
 */
public class ExamDAO {

    public List<ExamDTO> getExamsByCategory(String categoryName) {
        List<ExamDTO> exams = new ArrayList<>();
        String sql = "SELECT e.exam_id, e.exam_title, e.subject, e.category_id, e.total_marks, e.duration, e.created_by "
                + "FROM tblExams e JOIN tblExamCategories c ON e.category_id = c.category_id "
                + "WHERE c.category_name = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoryName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int examId = rs.getInt("exam_id");
                    String examTitle = rs.getString("exam_title");
                    String subject = rs.getString("subject");
                    int categoryId = rs.getInt("category_id"); // Lấy luôn categoryId để đồng bộ với DTO
                    int totalMarks = rs.getInt("total_marks");
                    int duration = rs.getInt("duration");
                    String createdBy = rs.getString("created_by");

                    exams.add(new ExamDTO(examId, examTitle, subject, categoryId, totalMarks, duration, createdBy));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exams;
    }

    public boolean createExam(ExamDTO exam) throws SQLException, ClassNotFoundException {
        boolean check = false;
        String sql = "INSERT INTO tblExams(exam_title, subject, category_id, total_marks, duration, created_by) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, exam.getExamTitle());
            stm.setString(2, exam.getSubject());
            stm.setInt(3, exam.getCategoryId());
            stm.setInt(4, exam.getTotalMarks());
            stm.setInt(5, exam.getDuration());
            stm.setString(6, exam.getCreatedBy());

            check = stm.executeUpdate() > 0;
        }
        return check;
    }

    public static boolean updateExam(ExamDTO exam) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblExams SET exam_title = ?, subject = ?, category_id = ?, total_marks = ?, duration = ? WHERE exam_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, exam.getExamTitle());
            ps.setString(2, exam.getSubject());
            ps.setInt(3, exam.getCategoryId());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDuration());
            ps.setInt(6, exam.getExamId());

            return ps.executeUpdate() > 0;
        }
    }

    public static boolean deleteExam(int examId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tblExams WHERE exam_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, examId);
            return ps.executeUpdate() > 0;
        }
    }
}
