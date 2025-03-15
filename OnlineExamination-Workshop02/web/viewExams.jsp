<%-- 
    Document   : viewExams
    Created on : Mar 15, 2025, 2:23:33 PM
    Author     : huuduy
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO" %>
<%@page import="dto.ExamDTO" %>
<%@page import="dto.CategoryDTO" %>
<%@page import="java.util.List" %>
<%
    HttpSession sessionObj = request.getSession(false);
    UserDTO user = (sessionObj != null) ? (UserDTO) sessionObj.getAttribute("USER") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("CATEGORY_LIST");
    List<ExamDTO> examList = (List<ExamDTO>) request.getAttribute("EXAM_LIST");
    String selectedCategory = (String) request.getAttribute("SELECTED_CATEGORY");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Exams by Categories</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="container">
            <h2>View Exams by Categories</h2>

            <!-- Filter Exam by Category -->
            <form action="MainController" method="GET">
                <label for="category">Select Category:</label>
                <select name="category" id="category" required>
                    <option value="">-- Choose Category --</option>
                    <% if (categoryList != null) {
                            for (CategoryDTO category : categoryList) {%>
                    <option value="<%= category.getCategoryName()%>" <%= (category.getCategoryName().equals(selectedCategory)) ? "selected" : ""%> >
                        <%= category.getCategoryName()%>
                    </option>
                    <%  }
                        } %>
                </select>
                <button type="submit" name="action" value="viewExams">Filter</button>
            </form>

            <!-- Exam List Table -->
            <table>
                <thead>
                    <tr>
                        <th>Exam Title</th>
                        <th>Subject</th>
                        <th>Total Marks</th>
                        <th>Duration (mins)</th>
                        <th>Take Exam</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (examList != null && !examList.isEmpty()) {
                            for (ExamDTO exam : examList) {%>
                    <tr>
                        <td><%= exam.getExamTitle()%></td>
                        <td><%= exam.getSubject()%></td>
                        <td><%= exam.getTotalMarks()%></td>
                        <td><%= exam.getDuration()%></td>
                        <td>
                            <a href="MainController?action=takeExam&examId=<%= exam.getExamId()%>">Take</a>
                        </td>
                        <td>
                            <a href="MainController?action=takeExam&examId=<%= exam.getExamId()%>">Edit</a>
                        </td>
                    </tr>
                    <%  }
                    } else { %>
                    <tr>
                        <td colspan="4">No exams available for this category.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>

            <a href="home.jsp" class="back-btn">Back to Home</a>
        </div>
    </body>
</html>

<style>
    body { font-family: Arial, sans-serif; background: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh; }
    .container { width: 600px; padding: 20px; background: white; box-shadow: 0px 0px 10px gray; border-radius: 5px; text-align: center; }
    select, button { padding: 8px; margin: 10px 0; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    table, th, td { border: 1px solid black; }
    th, td { padding: 10px; text-align: center; }
    .back-btn { display: inline-block; margin-top: 15px; padding: 8px 12px; background: blue; color: white; text-decoration: none; border-radius: 5px; }
</style>

