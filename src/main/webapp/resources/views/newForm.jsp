<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Новый Сотрудник</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>

<form action="${pageContext.request.contextPath}/insert">
    <fieldset>
        <legend>Создание нового сотрудника</legend>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name"><br>

        <label for="last_name">Фамилия:</label>
        <input type="text" id="last_name" name="last_name"><br>

        <label for="id_position">Должность:</label>
        <select id="id_position" name="id_position">
            <c:forEach var="position" items="${requestScope.positionsList}">
                <option value="${position.id}">${position.name_position}</option>
            </c:forEach>
        </select><br>
        <p><input type="submit" value="Отправить информацию"></p>
    </fieldset>
</form>
</body>
</html>
