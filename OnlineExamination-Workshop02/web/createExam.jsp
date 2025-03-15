<%-- 
    Document   : createExam
    Created on : Mar 15, 2025, 3:20:07 PM
    Author     : huudu
--%>

<%-- 
    Document   : createExam
    Created on : Mar 15, 2025, 3:20:07 PM
    Author     : huudu
--%>

<%-- 
    Document   : createExam
    Created on : Mar 15, 2025, 3:20:07 PM
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO" %>
<%@page import="dto.CategoryDTO" %>
<%@page import="java.util.List" %>
<%
    HttpSession sessionObj = request.getSession(false);
    UserDTO user = (sessionObj != null) ? (UserDTO) sessionObj.getAttribute("USER") : null;
    if (user == null || !"Instructor".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("CATEGORY_LIST");
    String errorMessage = (String) request.getAttribute("ERROR_MESSAGE");
    String successMessage = (String) request.getAttribute("MESSAGE"); // Thêm biến cho thông báo thành công
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create New Exam</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="container">
            <h2>Create New Exam</h2>


            <% if (successMessage != null) {%>
            <p class="success"><%= successMessage%></p>
            <% } %>

            <form action="MainController" method="POST">
                <label for="examTitle">Exam Title:</label>
                <input type="text" id="examTitle" name="examTitle" required>

                <label for="subject">Subject:</label>
                <input type="text" id="subject" name="subject" required>

                <label for="category">Category:</label>
                <select id="category" name="categoryId" required>
                    <option value="">-- Choose Category --</option>
                    <% if (categoryList != null) {
                            for (CategoryDTO category : categoryList) {%>
                    <option value="<%= category.getCategoryId()%>">
                        <%= category.getCategoryName()%>
                    </option>
                    <%  }
                        }%>
                </select>

                <label for="totalMarks">Total Marks:</label>
                <input type="number" id="totalMarks" name="totalMarks" required min="1">

                <label for="duration">Duration (mins):</label>
                <input type="number" id="duration" name="duration" required min="1">

                <div class="button-group">
                    <button type="submit" name="action" value="createExam">Create Exam</button>
                    <button type="reset">Reset</button>
                </div>
            </form>

            <a href="home.jsp" class="back-btn">Back to Home</a>
        </div>
    </body>
</html>

<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { 
        font-family: Arial, sans-serif; 
        background: #f4f4f4; 
        display: flex; 
        justify-content: center; 
        align-items: center; 
        height: 100vh; 
    }
    .container { 
        width: 400px; 
        padding: 20px; 
        background: white; 
        box-shadow: 0px 0px 10px gray; 
        border-radius: 5px; 
        text-align: center; 
        display: flex; 
        flex-direction: column; 
        align-items: center;
    }
    label { 
        align-self: flex-start; 
        margin-bottom: 5px; 
        font-weight: bold;
    }
    input, select, button { 
        width: 100%; 
        margin-bottom: 10px; 
        padding: 8px; 
        border: 1px solid #ccc; 
        border-radius: 5px; 
        text-align: center; 
        appearance: none;
    }
    select { 
        text-align-last: center;
    }
    .button-group {
        display: flex;
        gap: 10px; /* Khoảng cách giữa hai nút */
        width: 100%;
    }
    button { 
        background: blue; 
        color: white; 
        border: none; 
        cursor: pointer; 
        font-size: 16px;
        flex: 1; /* Hai nút chiếm đều không gian */
    }
    button[type="reset"] {
        background: #ff4444; /* Màu đỏ cho nút Reset */
    }
    button:hover { 
        background: darkblue; 
    }
    button[type="reset"]:hover {
        background: #cc0000; /* Màu đỏ đậm khi hover */
    }
    .back-btn { 
        display: block; 
        width: 100%; 
        padding: 10px; 
        background: gray; 
        color: white; 
        text-decoration: none; 
        text-align: center; 
        border-radius: 5px; 
    }
    .error { 
        color: red; 
        font-weight: bold; 
        margin-bottom: 10px;
    }
    .success { 
        color: green; /* Màu xanh cho thông báo thành công */
        font-weight: bold; 
        margin-bottom: 10px;
    }
</style>


