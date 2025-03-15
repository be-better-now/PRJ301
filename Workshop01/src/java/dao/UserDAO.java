/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import dto.UserDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author huuduy
 */
public class UserDAO implements IDAO<UserDTO, String> {

    @Override
    public boolean create(UserDTO user) {
        String sql = "INSERT INTO tblUsers (username, fullName, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserDTO> readAll() {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM tblUsers";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(new UserDTO(rs.getString("username"), rs.getString("name"), 
                                      rs.getString("password"), rs.getString("role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public UserDTO readById(String username) { // Đổi userID thành username
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement("SELECT * FROM tblUsers WHERE username = ?")) {
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new UserDTO(rs.getString("username"),
                               rs.getString("name"),
                               rs.getString("password"),
                               rs.getString("role"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    @Override
    public boolean update(UserDTO user) {
        String sql = "UPDATE tblUsers SET name = ?, password = ?, role = ? WHERE username = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String userID) {
        String sql = "DELETE FROM tblUsers WHERE userID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserDTO> search(String searchTerm) {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM tblUsers WHERE username LIKE ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(new UserDTO(rs.getString("username"), rs.getString("name"),
                                          rs.getString("password"), rs.getString("role")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<UserDTO> searchByName(String searchTerm) {
        return null;
    }
    
    @Override
    public List<UserDTO> getProjectsByUser(String username) {
        return null;
    }
}
