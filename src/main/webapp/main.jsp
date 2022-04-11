<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="UTF-8">
    <style>
        <%@include file="/resources/css/table.css"%>
    </style>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Должность</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Удалить</th>
        <th>Изменить</th>
    </tr>

    <c:forEach var="l" items="${requestScope.list}">
        <tr>
            <td>${l.id}</td>
            <td>${l.name_position}</td>
            <td>${l.name}</td>
            <td>${l.last_name}</td>
            <td><a href="${pageContext.request.contextPath}/delete?id=<c:out value='${l.id}' />">удалить</a></td>
            <td><a href="${pageContext.request.contextPath}/edit?id=<c:out value='${l.id}' />">изменить</a></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}">На главную</a>
</body>
</html>
