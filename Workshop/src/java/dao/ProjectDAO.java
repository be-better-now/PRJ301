/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author huudu
 */
public class ProjectDAO {
    private static final String CREATE_PROJECT_SQL = 
        "INSERT INTO projects (project_name, description, status, estimated_launch) VALUES (?, ?, ?, ?)";
    
    
    public List<ProjectDTO> getAllProjects() throws Exception {
        List<ProjectDTO> projectList = new ArrayList<>();
        String sql = "SELECT * FROM tblStartupProjects";
        
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                projectList.add(new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("launch_date")
                ));
            }
        }
        return projectList;
    }
    
    public boolean createProject(int projectID, String projectName, String description, String status, String estimatedLaunch) {
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean isCreated = false;

        try {
            // Load JDBC Driver (nếu cần)

            conn = DBUtils.getConnection();
            if (conn != null) {
                pstm = conn.prepareStatement(CREATE_PROJECT_SQL);
                pstm.setInt(1, projectID);
                pstm.setString(2, projectName);
                pstm.setString(3, description);
                pstm.setString(4, status);

                // Chuyển đổi String thành java.sql.Date
                if (estimatedLaunch != null && !estimatedLaunch.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = sdf.parse(estimatedLaunch);
                    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                    pstm.setDate(5, sqlDate);
                } else {
                    pstm.setNull(5, java.sql.Types.DATE);
                }

                // Thực thi truy vấn
                int rowsAffected = pstm.executeUpdate();
                isCreated = rowsAffected > 0;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn, pstm, null);
        }

        return isCreated;
    }



    public boolean updateProjectStatus(int projectId, String newStatus) throws Exception {
        String sql = "UPDATE Projects SET status = ? WHERE project_id = ?";
        
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, newStatus);
            ps.setInt(2, projectId);

            return ps.executeUpdate() > 0;
        }
    }

    public List<ProjectDTO> searchProjectsByName(String keyword) throws Exception {
        List<ProjectDTO> projectList = new ArrayList<>();
        String sql = "SELECT * FROM Projects WHERE project_name LIKE ?";
        
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    projectList.add(new ProjectDTO(
                            rs.getInt("project_id"),
                            rs.getString("project_name"),
                            rs.getString("description"),
                            rs.getString("status"),
                            rs.getString("launch_date")
                    ));
                }
            }
        }
        return projectList;
    }
}

