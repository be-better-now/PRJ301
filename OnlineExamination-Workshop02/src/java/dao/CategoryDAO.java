/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CategoryDTO;
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
public class CategoryDAO {

    public static List<CategoryDTO> getAllCategories() throws SQLException, ClassNotFoundException {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT category_name, description FROM tblExamCategories";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new CategoryDTO(rs.getString("category_name"), rs.getString("description")));
            }
        }
        return list;
    }
    
    public static List<CategoryDTO> getAllCategories2() throws SQLException, ClassNotFoundException {
    List<CategoryDTO> list = new ArrayList<>();
    String sql = "SELECT category_id, category_name, description FROM tblExamCategories"; // Thêm categoryId
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            list.add(new CategoryDTO(rs.getInt("category_id"), rs.getString("category_name"), rs.getString("description")));
        }
    }
    return list;
}

}
