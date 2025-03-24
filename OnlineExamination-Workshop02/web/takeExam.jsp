<%-- 
    Document   : takeExam
    Created on : Mar 15, 2025
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO" %>
<%@page import="dto.ExamDTO" %>
<%@page import="dto.QuestionDTO" %>
<%@page import="java.util.List" %>
<%
    HttpSession sessionObj = request.getSession(false);
    UserDTO user = (sessionObj != null) ? (UserDTO) sessionObj.getAttribute("USER") : null;
    if (user == null || !"Student".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<ExamDTO> examList = (List<ExamDTO>) request.getAttribute("EXAM_LIST");
    List<QuestionDTO> questionList = (List<QuestionDTO>) request.getAttribute("QUESTION_LIST");
    String selectedExamId = request.getParameter("examId");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Take Exam</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="home-container">
        <h1 class="title">ONLINE EXAMINATION</h1>
        <h2>Welcome, <%= user.getName() %>!</h2>

        <% if (examList != null && questionList == null) { %>
            <h3>Select an Exam to Take</h3>
            <form action="MainController" method="POST">
                <select name="examId" required>
                    <option value="">-- Choose an Exam --</option>
                    <% for (ExamDTO exam : examList) { %>
                        <option value="<%= exam.getExamId() %>"><%= exam.getExamTitle() %></option>
                    <% } %>
                </select>
                <button type="submit" name="action" value="startExam">Start Exam</button>
            </form>
        <% } else if (questionList != null) { %>
            <h3><%= ((ExamDTO) request.getAttribute("SELECTED_EXAM")).getExamTitle() %></h3>
            <form action="MainController" method="POST">
                <% int index = 1;
                   for (QuestionDTO question : questionList) { %>
                    <div class="question">
                        <p><strong>Question <%= index++ %>:</strong> <%= question.getQuestionText() %></p>
                        <label><input type="radio" name="answer_<%= question.getQuestionId() %>" value="A" required> <%= question.getOptionA() %></label><br>
                        <label><input type="radio" name="answer_<%= question.getQuestionId() %>" value="B"> <%= question.getOptionB() %></label><br>
                        <label><input type="radio" name="answer_<%= question.getQuestionId() %>" value="C"> <%= question.getOptionC() %></label><br>
                        <label><input type="radio" name="answer_<%= question.getQuestionId() %>" value="D"> <%= question.getOptionD() %></label><br>
                    </div>
                <% } %>
                <input type="hidden" name="examId" value="<%= selectedExamId %>">
                <button type="submit" name="action" value="submitExam">Submit Exam</button>
            </form>
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
        width: 600px; /* Tăng chiều rộng để chứa câu hỏi */
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
    select, button { 
        padding: 8px; 
        margin: 10px 0; 
        border-radius: 5px; 
    }
    button { 
        background: blue; 
        color: white; 
        border: none; 
        cursor: pointer; 
    }
    button:hover { 
        background: darkblue; 
    }
    .question { 
        text-align: left; 
        margin: 15px 0; 
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
</style>