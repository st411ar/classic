<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Paper Library Orders</title>
    </head>
    <body>
        <h1>Paper Library Orders</h1>
        <a href="index.jsp">Home</a>
        <table border="1">
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.book.name}</td>
                    <td>${order.user.name}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>