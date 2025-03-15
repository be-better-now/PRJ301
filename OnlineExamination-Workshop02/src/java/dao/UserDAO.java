/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;
import dto.UserDTO;

/**
 *
 * @author huudu
 */
public class UserDAO {

    public static UserDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        String sql = "SELECT * FROM tblUsers WHERE username = ? AND password = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new UserDTO(
                            rs.getString("username"),
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }
        }
        return user;
    }

    public static boolean register(UserDTO user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblUsers (username, name, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            return ps.executeUpdate() > 0;
        }
    }
}
