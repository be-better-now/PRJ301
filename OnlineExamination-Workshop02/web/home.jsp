<%-- 
    Document   : home
    Created on : Mar 15, 2025, 1:44:15 PM
    Author     : huuduy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession(false);
    UserDTO user = (sessionObj != null) ? (UserDTO) sessionObj.getAttribute("USER") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String role = user.getRole();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="home-container">
        <h1 class="title">ONLINE EXAMINATION</h1>
        <h2>Welcome, <%= user.getName() %>!</h2>
        <nav>
            <ul>
                <% if ("Instructor".equals(role)) { %>
                    <li><a href="MainController?action=viewExams">View Exams by Categories</a></li>
                    <li><a href="MainController?action=viewCategories">View Exam Categories</a></li>
                    <li><a href="MainController?action=createExam">Create New Exam with Category</a></li>
                    <li><a href="MainController?action=addQuestion">Add Questions</a></li>
                <% } else if ("Student".equals(role)) { %>
                    <li><a href="MainController?action=takeExam">Take Exam</a></li>
                    <li><a href="MainController?action=viewCategories">View Exam Categories</a></li>
                    <li><a href="MainController?action=viewExams">View Exam by Categories</a></li>
                    <li><a href="MainController?action=viewResults">View Exam Results</a></li>
                <% } %>
                <li><a href="MainController?action=logout">Logout</a></li>
            </ul>
        </nav>
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
    }
    .home-container { 
        width: 400px; 
        padding: 20px; 
        background: white; 
        box-shadow: 0px 0px 10px gray; 
        border-radius: 5px; 
        text-align: center; 
    }
    .title { 
        font-size: 36px; /* Chữ to */
        font-weight: bold; 
        color: #333; /* Màu xám đậm */
        margin-bottom: 20px; /* Khoảng cách với phần bên dưới */
    }
    h2 {
        font-size: 24px; /* Chào mừng nhỏ hơn tiêu đề */
        margin-bottom: 20px;
    }
    nav ul { 
        list-style: none; 
        padding: 0; 
    }
    nav ul li { 
        margin: 10px 0; 
    }
    nav ul li a { 
        text-decoration: none; 
        padding: 10px; 
        display: block; 
        background: blue; 
        color: white; 
        border-radius: 5px; 
    }
    nav ul li a:hover {
        background: darkblue; /* Hover hiệu ứng */
    }
</style>
   