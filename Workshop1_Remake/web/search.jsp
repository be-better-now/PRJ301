<%-- 
    Document   : search
    Created on : Mar 9, 2025, 2:35:03 PM
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Projects</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 60%;
            margin: 50px auto;
            padding: 20px;
            background: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        form {
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 8px;
            width: 60%;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 8px 15px;
            background: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Search Projects</h2>
        
        <form action="MainController" method="GET">
            <input type="hidden" name="action" value="searchProject">
            <input type="text" name="searchTerm" placeholder="Enter project name..." value="${searchTerm}">
            <button type="submit">Search</button>
        </form>

        <c:if test="${not empty message}">
            <p class="error-message">${message}</p>
        </c:if>

        <c:if test="${not empty projects}">
            <table>
                <tr>
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Launch Date</th>
                </tr>
                <c:forEach var="project" items="${projects}">
                    <tr>
                        <td>${project.projectName}</td>
                        <td>${project.description}</td>
                        <td>${project.status}</td>
                        <td>${project.launchDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>


