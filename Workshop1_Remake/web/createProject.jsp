<%-- 
    Document   : createProject
    Created on : Mar 9, 2025, 2:09:43 PM
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO" %>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null || !"FD".equals(user.getRole())) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Project</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <h3>Create a New Project</h3>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="createProject">
            <div class="mb-3">
                <label class="form-label">Project Name</label>
                <input type="text" name="project_name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea name="description" class="form-control" required></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Status</label>
                <select name="status" class="form-select">
                    <option value="Ideation">Ideation</option>
                    <option value="Development">Development</option>
                    <option value="Launch">Launch</option>
                    <option value="Scaling">Scaling</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Estimated Launch Date</label>
                <input type="date" name="estimated_launch" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-success">Create</button>
            <a href="dashboard.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>

