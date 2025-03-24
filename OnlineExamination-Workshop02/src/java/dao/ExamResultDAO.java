/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamResultDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author huudu
 */
public class ExamResultDAO {

    public boolean saveExamResult(ExamResultDTO result) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblExamResults (exam_id, student, score) VALUES (?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, result.getExamId());
            ps.setString(2, result.getStudent());
            ps.setInt(3, result.getScore());
            return ps.executeUpdate() > 0;
        }
    }

    public List<ExamResultDTO> getResultsByStudent(String student) throws SQLException, ClassNotFoundException {
        List<ExamResultDTO> list = new ArrayList<>();
        String sql = "SELECT result_id, exam_id, student, score FROM tblExamResults WHERE student = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ExamResultDTO(rs.getInt("result_id"), rs.getInt("exam_id"), rs.getString("student"), rs.getInt("score")));
                }
            }
        }
        return list;
    }
}
