<%-- 
    Document   : example02
    Created on : Feb 10, 2025, 1:20:35 PM
    Author     : huudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            for(int i = 0; i < 100; i++) {
            %>
                <%=i%> <br/>
            <%
            }
        %>
    </body>
</html>
