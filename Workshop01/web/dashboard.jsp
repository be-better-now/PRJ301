    <%-- 
        Document   : dashboard
        Created on : Mar 9, 2025, 2:05:28 PM
        Author     : huuduy
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="dto.UserDTO, dto.ProjectDTO, java.util.List" %>
    <%
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("projects");
        String errorMessage = (String) request.getAttribute("errorMessage");
    %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Startup Management</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <nav class="navbar navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Startup Management</a>
                <span class="text-white">Welcome, <%= user.getFullName() %></span>
                <a href="MainController?action=logout" class="btn btn-danger">Logout</a>
            </div>
        </nav>

        <div class="container mt-4">
            <h3>Startup Projects</h3>

            <% if (errorMessage != null) { %>
            <div class="alert alert-danger"><%= errorMessage %></div>
            <% } %>

            <% if ("Founder".equals(user.getRole())) { %>
            <div class="mb-3">
                <a href="createProject.jsp" class="btn btn-success">+ Create Project</a>
            </div>
            <form action="MainController" method="get" class="mb-3">
                <input type="hidden" name="action" value="searchProject">
                <div class="input-group">
                    <input type="text" name="searchTerm" class="form-control" placeholder="Search project name">
                    <button class="btn btn-primary">Search</button>
                </div>
            </form>
            <% } %>

            <table class="table table-bordered bg-white">
                <thead class="table-dark">
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Launch Date</th>
                        <% if ("Founder".equals(user.getRole())) { %>
                        <th>Actions</th>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                    <% if (projects != null && !projects.isEmpty()) {
                        for (ProjectDTO project : projects) { %>
                    <tr>
                        <td><%= project.getProjectName() %></td>
                        <td><%= project.getDescription() %></td>
                        <td><%= project.getStatus() %></td>
                        <td><%= project.getLaunchDate() %></td>
                        <% if ("Founder".equals(user.getRole())) { %>
                        <td>
                            <a href="updateProject.jsp?id=<%= project.getProjectID() %>" class="btn btn-warning btn-sm">Edit</a>
                        </td>
                        <% } %>
                    </tr>
                    <% } } else { %>
                    <tr><td colspan="5" class="text-center">No projects found</td></tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </body>
    </html>
