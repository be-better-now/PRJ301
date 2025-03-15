<%-- 
    Document   : login
    Created on : Mar 15, 2025, 1:43:35 PM
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <% if (request.getAttribute("ERROR") != null) { %>
            <p class="error"><%= request.getAttribute("ERROR") %></p>
        <% } %>
        <form action="MainController" method="POST">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit" name="action" value="login">Login</button>
        </form>
    </div>
</body>
</html>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .login-container {
        width: 350px;
        padding: 20px;
        background: white;
        box-shadow: 0px 0px 10px gray;
        border-radius: 10px;
        text-align: center;
    }
    input {
        width: calc(100% - 20px);
        padding: 10px;
        margin: 10px 0;
        border-radius: 5px;
        border: 1px solid #ddd;
        display: block;
    }
    button {
        width: 100%;
        padding: 10px;
        background: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        font-size: 16px;
    }
    .error {
        color: red;
        font-size: 14px;
    }
</style>