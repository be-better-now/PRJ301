<%-- 
    Document   : viewCategories
    Created on : Mar 15, 2025
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDTO, java.util.List, dto.CategoryDTO" %>

<%
    HttpSession sessionObj = request.getSession(false);
    UserDTO user = (sessionObj != null) ? (UserDTO) sessionObj.getAttribute("USER") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("CATEGORIES");
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Exam Categories</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h2>Exam Categories</h2>
        <table>
            <thead>
                <tr>
                    <th>Category Name</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <% if (categories != null && !categories.isEmpty()) { 
                    for (CategoryDTO category : categories) { %>
                        <tr>
                            <td><%= category.getCategoryName()%></td>
                            <td><%= category.getDescription() %></td>
                        </tr>
                <%  } 
                   } else { %>
                    <tr><td colspan="2">No categories available</td></tr>
                <% } %>
            </tbody>
        </table>
        <a href="home.jsp" class="back-btn">Back to Home</a>
    </div>
</body>
</html>

<style>
    body { font-family: Arial, sans-serif; background: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh; }
    .container { width: 500px; padding: 20px; background: white; box-shadow: 0px 0px 10px gray; border-radius: 5px; text-align: center; }
    table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }
    th { background: blue; color: white; }
    .back-btn { display: inline-block; margin-top: 10px; padding: 10px; background: blue; color: white; text-decoration: none; border-radius: 5px; }
</style>
