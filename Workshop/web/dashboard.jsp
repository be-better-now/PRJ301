<%-- 
    Document   : dashboard
    Created on : Mar 5, 2025, 10:46:49 PM
    Author     : huuduy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO, dto.ProjectDTO, java.util.List"%>
<%
    UserDTO user = (UserDTO) session.getAttribute("USER");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Project Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= user.getUsername() %></h2>
    <a href="MainController?action=logout">Logout</a>

    <h3>Startup Projects</h3>
    <form action="MainController" method="POST">
        <input type="submit" name="action" value="viewProjects">
    </form>
    <form action="MainController" method="POST">
        <input type="text" name="keyword" placeholder="Search by name">
        <input type="submit" name="action" value="searchProject">
    </form>
    
    <%
        List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("PROJECTS");
        if (projects != null && !projects.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Launch Date</th>
                <th>Update</th>
            </tr>
    <%
            for (ProjectDTO project : projects) {
    %>
            <tr>
                <td><%= project.getProjectId() %></td>
                <td><%= project.getProjectName() %></td>
                <td><%= project.getDescription() %></td>
                <td><%= project.getStatus() %></td>
                <td><%= project.getEstimatedLaunch() %></td>
                <td>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="projectId" value="<%= project.getProjectId() %>">
                        <select name="status">
                            <option value="Development">Development</option>
                            <option value="Launch">Launch</option>
                        </select>
                        <input type="submit" name="action" value="updateStatus">
                    </form>
                </td>
            </tr>
    <%
            }
    %>
        </table>
    <%
        } else {
    %>
        <p>No projects found.</p>
    <%
        }
    %>

    <h3>Create New Project</h3>
    <form action="MainController" method="POST">
        <label>Name:</label>
        <input type="text" name="name" required>
        <br>
        <label>Description:</label>
        <input type="text" name="description" required>
        <br>
        <label>Status:</label>
        <input type="text" name="status" required>
        <br>
        <label>Launch Date:</label>
        <input type="date" name="launchDate" required>
        <br>
        <input type="submit" name="action" value="createProject">
    </form>

    <%
        String message = (String) request.getAttribute("MESSAGE");
        if (message != null) {
    %>
        <p style="color: green;"><%= message %></p>
    <%
        }
    %>
</body>
</html>

