<%-- 
    Document   : examResult
    Created on : Mar 15, 2025
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO" %>
<%@page import="dto.ExamResultDTO" %>
<%@page import="java.util.List" %>
<%
    HttpSession sessionObj = request.getSession(false);
    UserDTO user = (sessionObj != null) ? (UserDTO) sessionObj.getAttribute("USER") : null;
    if (user == null || !"Student".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    Integer score = (Integer) request.getAttribute("SCORE");
    List<ExamResultDTO> resultList = (List<ExamResultDTO>) request.getAttribute("RESULT_LIST");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Exam Results</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="home-container">
        <h1 class="title">ONLINE EXAMINATION</h1>
        <h2>Welcome, <%= user.getName() %>!</h2>

        <% if (score != null) { %>
            <p class="success">Your score: <%= score %></p>
        <% } %>

        <h3>Your Exam Results</h3>
        <% if (resultList != null && !resultList.isEmpty()) { %>
            <table>
                <tr>
                    <th>Exam ID</th>
                    <th>Score</th>
                </tr>
                <% for (ExamResultDTO result : resultList) { %>
                    <tr>
                        <td><%= result.getExamId() %></td>
                        <td><%= result.getScore() %></td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>No results available.</p>
        <% } %>

        <a href="home.jsp" class="back-btn">Back to Home</a>
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
        font-size: 36px; 
        font-weight: bold; 
        color: #333; 
        margin-bottom: 20px; 
    }
    h2 { 
        font-size: 24px; 
        margin-bottom: 20px; 
    }
    h3 { 
        margin-bottom: 15px; 
    }
    .success { 
        color: green; 
        font-weight: bold; 
        margin-bottom: 10px; 
    }
    table { 
        width: 100%; 
        border-collapse: collapse; 
        margin-bottom: 20px; 
    }
    th, td { 
        border: 1px solid #ccc; 
        padding: 8px; 
    }
    th { 
        background: #f0f0f0; 
    }
    .back-btn { 
        display: block; 
        width: 100%; 
        padding: 10px; 
        background: gray; 
        color: white; 
        text-decoration: none; 
        border-radius: 5px; 
    }
</style>