<%-- 
    Document   : updateProject
    Created on : Mar 9, 2025, 2:10:09 PM
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO, dto.ProjectDTO, dao.ProjectDAO"%>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null || !"FD".equals(user.getRole())) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    int projectId = Integer.parseInt(request.getParameter("id"));
    ProjectDAO projectDAO = new ProjectDAO();
    ProjectDTO project = projectDAO.readById(projectId);

    if (project == null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Project</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <h3>Update Project Status</h3>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="updateProject">
            <input type="hidden" name="id" value="<%= project.getProjectID() %>">

            <div class="mb-3">
                <label class="form-label">Project Name</label>
                <input type="text" class="form-control" value="<%= project.getProjectName() %>" disabled>
            </div>
            <div class="mb-3">
                <label class="form-label">Current Status</label>
                <input type="text" class="form-control" value="<%= project.getStatus() %>" disabled>
            </div>
            <div class="mb-3">
                <label class="form-label">New Status</label>
                <select name="status" class="form-select">
                    <option value="Planning" <%= "Planning".equals(project.getStatus()) ? "selected" : "" %>>Planning</option>
                    <option value="Development" <%= "Development".equals(project.getStatus()) ? "selected" : "" %>>Development</option>
                    <option value="Launch" <%= "Launch".equals(project.getStatus()) ? "selected" : "" %>>Launch</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
            <a href="dashboard.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>

