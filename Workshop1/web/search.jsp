<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, dto.ProjectDTO"%>
<!DOCTYPE html>
<html>
<head>
    <title>Startup Projects</title>
</head>
<body>
    <h2>Startup Projects</h2>
    <form action="MainController" method="POST">
        <input type="submit" name="action" value="search">
    </form>

    <%
        List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("PROJECTS");
        if (projects != null && !projects.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Project ID</th>
                <th>Project Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Estimated Launch</th>
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
</body>
</html>
