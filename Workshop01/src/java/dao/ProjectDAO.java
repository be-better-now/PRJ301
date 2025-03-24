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
    public List<ProjectDTO> getProjectsByUser(String username) {
        List<ProjectDTO> projectList = new ArrayList<>();
        String sql = "SELECT p.project_id, p.project_name, p.Description, p.Status, p.estimated_launch "
                + "FROM tblStartupProjects p "
                + "JOIN tblUserProjects up ON p.project_id = up.project_id "
                + "WHERE up.username = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projectList.add(new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log lỗi ra console
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Log lỗi class không tìm thấy
        }
        return projectList;
    }

    @Override
    public boolean create(ProjectDTO project) {
        String sql = "INSERT INTO tblStartupProjects (project_name, description, status, estimated_launch, founder_username) VALUES (?, ?, ?, ?, ?)";
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
        String sql = "SELECT * FROM tblStartupProjects";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                projects.add(new ProjectDTO(rs.getInt("project_id"), rs.getString("projectName"),
                        rs.getString("description"), rs.getString("status"),
                        rs.getDate("launchDate"), rs.getString("founderID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public ProjectDTO readById(Integer project_id) {
        String sql = "SELECT * FROM tblStartupProjects WHERE project_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, project_id);
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
        String sql = "UPDATE tblStartupProjects SET projectName = ?, description = ?, status = ?, launchDate = ? WHERE project_id = ?";
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
        String sql = "DELETE FROM tblStartupProjects WHERE project_id = ?";
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
        String sql = "SELECT * FROM tblStartupProjects WHERE projectName LIKE ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectDTO(rs.getInt("project_id"), rs.getString("projectName"),
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
        String sql = "SELECT * FROM tblStartupProjects WHERE project_name LIKE ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                projects.add(new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getDate("estimated_launch"),
                        rs.getString("founder_username") // Thêm founder_username
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Search Error: " + e.getMessage());
            e.printStackTrace();
        }
        return projects;
    }

}
