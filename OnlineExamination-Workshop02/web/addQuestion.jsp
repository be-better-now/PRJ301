<%-- 
    Document   : addQuestion
    Created on : Mar 15, 2025
    Author     : huuduy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO" %>
<%@page import="dto.ExamDTO" %>
<%@page import="java.util.List" %>
<%
    HttpSession sessionObj = request.getSession(false);
    UserDTO user = (sessionObj != null) ? (UserDTO) sessionObj.getAttribute("USER") : null;
    if (user == null || !"Instructor".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<ExamDTO> examList = (List<ExamDTO>) request.getAttribute("EXAM_LIST");
    String message = (String) request.getAttribute("MESSAGE");
    String errorMessage = (String) request.getAttribute("ERROR_MESSAGE");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Questions</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="home-container">
        <h1 class="title">ONLINE EXAMINATION</h1>
        <h2>Welcome, <%= user.getName() %>!</h2>

        <% if (message != null) { %>
            <p class="success"><%= message %></p>
        <% } %>
        <% if (errorMessage != null) { %>
            <p class="error"><%= errorMessage %></p>
        <% } %>

        <h3>Add a New Question</h3>
        <form action="MainController" method="POST">
            <label for="examId">Select Exam:</label>
            <select id="examId" name="examId" required>
                <option value="">-- Choose an Exam --</option>
                <% if (examList != null) {
                       for (ExamDTO exam : examList) { %>
                    <option value="<%= exam.getExamId() %>"><%= exam.getExamTitle() %></option>
                <%    }
                   } %>
            </select>

            <label for="questionText">Question Text:</label>
            <textarea id="questionText" name="questionText" required rows="3"></textarea>

            <label for="optionA">Option A:</label>
            <input type="text" id="optionA" name="optionA" required>

            <label for="optionB">Option B:</label>
            <input type="text" id="optionB" name="optionB" required>

            <label for="optionC">Option C:</label>
            <input type="text" id="optionC" name="optionC" required>

            <label for="optionD">Option D:</label>
            <input type="text" id="optionD" name="optionD" required>

            <label for="correctOption">Correct Option:</label>
            <select id="correctOption" name="correctOption" required>
                <option value="">-- Choose Correct Option --</option>
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>

            <div class="button-group">
                <button type="submit" name="action" value="addQuestion">Add Question</button>
                <button type="reset">Reset</button>
            </div>
        </form>

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
        width: 500px; /* Tăng chiều rộng cho form */
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
    label { 
        display: block; 
        text-align: left; 
        margin-bottom: 5px; 
        font-weight: bold; 
    }
    input, select, textarea { 
        width: 100%; 
        padding: 8px; 
        margin-bottom: 10px; 
        border: 1px solid #ccc; 
        border-radius: 5px; 
        text-align: center; 
    }
    textarea { 
        resize: vertical; 
    }
    .button-group { 
        display: flex; 
        gap: 10px; 
        width: 100%; 
    }
    button { 
        background: blue; 
        color: white; 
        border: none; 
        cursor: pointer; 
        font-size: 16px; 
        flex: 1; 
    }
    button[type="reset"] { 
        background: #ff4444; 
    }
    button:hover { 
        background: darkblue; 
    }
    button[type="reset"]:hover { 
        background: #cc0000; 
    }
    .back-btn { 
        display: block; 
        width: 100%; 
        padding: 10px; 
        background: gray; 
        color: white; 
        text-decoration: none; 
        border-radius: 5px; 
        margin-top: 20px; 
    }
    .success { 
        color: green; 
        font-weight: bold; 
        margin-bottom: 10px; 
    }
    .error { 
        color: red; 
        font-weight: bold; 
        margin-bottom: 10px; 
    }
</style>
