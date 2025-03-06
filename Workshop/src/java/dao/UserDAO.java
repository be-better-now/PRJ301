/*
DAO là một design pattern dùng để truy cập và thao tác với database
Chứa tất cả các phương thức CRUD (Create, Read, Update, Delete)
Đóng gói toàn bộ logic truy cập database
Tách biệt logic truy cập dữ liệu khỏi business logic
Giúp code dễ maintain và test hơn
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author huuduy
 */
public class UserDAO {
    public UserDTO login(String username, String password) {
        UserDTO user = null;
        String sql = "SELECT * FROM tblUsers WHERE Username = ? AND Password = ?";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new UserDTO(rs.getString("Username"),
                                   rs.getString("Name"),
                                   rs.getString("Password"),
                                   rs.getString("Role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return user;
    }
}