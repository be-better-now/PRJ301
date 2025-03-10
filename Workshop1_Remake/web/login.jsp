<%-- 
    Document   : login
    Created on : Mar 9, 2025, 2:05:28 PM
    Author     : huuduy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Startup Management</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="card p-4 shadow-lg" style="width: 350px;">
            <h3 class="text-center mb-3">Login</h3>
            <form action="MainController" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" name="txtUsername" id="username" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" name="txtPassword" id="password" class="form-control" required>
                </div>
                <button type="submit" name="action" value="login" class="btn btn-primary w-100">Login</button>
            </form>
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <div class="alert alert-danger mt-3"><%= message %></div>
            <% } %>
        </div>
    </div>
</body>
</html>

