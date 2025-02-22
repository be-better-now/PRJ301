<%-- 
    Document   : example04
    Created on : Feb 10, 2025, 1:36:55 PM
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
        <!-- IN BẢNG CỬU CHƯƠNG -->
        <%
            for (int i = 2; i <= 10; i++) {
                out.println("<h4>Bảng Cửu Chương " +i+"</h4>");
                for (int j = 1; j <= 10; j++) {
                    out.println(i+"x"+j+" = "+(i*j)+"<br/>");
                }
                out.println("<hr>");
            }
        %>
    </body>
</html>
