<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Форма изменения</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/update">
    <fieldset>
        <input type="hidden" id="id" name="id" value="<c:out value='${requestScope.id}' />">

        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" value="<c:out value='${requestScope.name}' />"><br>

        <label for="last_name">Фамилия:</label>
        <input type="text" id="last_name" name="last_name" value="<c:out value='${requestScope.last_name}' />"><br>

        <label for="id_position">Должность:</label>
        <select id="id_position" name="id_position">
            <c:forEach var="position" items="${requestScope.positionsList}">
                <option value="${position.id}">${position.name_position}</option>
            </c:forEach>
        </select><br>


        <input type="submit" value="Обновить информацию">
    </fieldset>

</form>

</body>
</html>


