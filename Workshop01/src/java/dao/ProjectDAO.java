/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import dto.ProjectDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author huuduy
 */
public class ProjectDAO implements IDAO<ProjectDTO, Integer> {

    @Override
    public boolean create(ProjectDTO project) {
        String sql = "INSERT INTO tblStartupProjects (projectName, description, status, launchDate, username) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getStatus());
            ps.setDate(4, project.getLaunchDate());
            ps.setString(5, project.getFounderID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    


    @Override
    public List<ProjectDTO> readAll() {
        List<ProjectDTO> projects = new ArrayList<>();
        String sql = "SELECT * FROM StartupProjects";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                projects.add(new ProjectDTO(rs.getInt("projectID"), rs.getString("projectName"),
                                            rs.getString("description"), rs.getString("status"),
                                            rs.getDate("launchDate"), rs.getString("founderID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public ProjectDTO readById(Integer projectID) {
        String sql = "SELECT * FROM StartupProjects WHERE projectID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ProjectDTO(rs.getInt("projectID"), rs.getString("projectName"),
                                          rs.getString("description"), rs.getString("status"),
                                          rs.getDate("launchDate"), rs.getString("founderID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(ProjectDTO project) {
        String sql = "UPDATE StartupProjects SET projectName = ?, description = ?, status = ?, launchDate = ? WHERE projectID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getStatus());
            ps.setDate(4, project.getLaunchDate());
            ps.setInt(5, project.getProjectID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer projectID) {
        String sql = "DELETE FROM StartupProjects WHERE projectID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProjectDTO> search(String searchTerm) {
        List<ProjectDTO> projects = new ArrayList<>();
        String sql = "SELECT * FROM StartupProjects WHERE projectName LIKE ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectDTO(rs.getInt("projectID"), rs.getString("projectName"),
                                                rs.getString("description"), rs.getString("status"),
                                                rs.getDate("launchDate"), rs.getString("founderID")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }
    
    @Override
    public List<ProjectDTO> searchByName(String searchTerm) {
    List<ProjectDTO> projects = new ArrayList<>();
    String sql = "SELECT * FROM tblProjects WHERE projectName LIKE ?";
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + searchTerm + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            projects.add(new ProjectDTO(
                rs.getInt("projectID"),
                rs.getString("projectName"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getDate("launchDate")
            ));
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return projects;
    }
    
}


