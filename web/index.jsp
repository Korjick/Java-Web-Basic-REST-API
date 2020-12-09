<%--
  Created by IntelliJ IDEA.
  User: Bulat
  Date: 11/12/2020
  Time: 1:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h2>Данный сайт представляет собой простой пример реализации REST с помощью Java Servlets.</h2>
    <p>Проект основан на таблице Users и, соответсвенно, на модели User с параметрами - name, age, isMarried</p>
    <ul>
        <li>Для получения пользователя: GET запрос на <strong>${pageContext.request.getContextPath()}/user?name=ИМЯ_ПОЛЬЗОВАТЕЛЯ&age=ВОЗРАСТ_ПОЛЬЗОВАТЕЛЯ&isMarried=ЗНАЧЕНИЕ_TRUE_ИЛИ_FALSE</strong>
            - для получения конкретного пользователя</li>
        <li>Для получения пользоватей: GET запрос <strong>${pageContext.request.getContextPath()}/user</strong>
            - для получения всех пользователей</li>
    </ul>
    <ul>
        <li>Для добавления пользователя: POST запрос на <strong>${pageContext.request.getContextPath()}/user?name=ИМЯ_ПОЛЬЗОВАТЕЛЯ&age=ВОЗРАСТ_ПОЛЬЗОВАТЕЛЯ&isMarried=ЗНАЧЕНИЕ_TRUE_ИЛИ_FALSE</strong>
            - для добавления конкретного пользователя</li>
    </ul>
    <ul>
        <li>Для обновления пользователя: PUT запрос на
            <strong>${pageContext.request.getContextPath()}/user?oldName=ИМЯ_ПОЛЬЗОВАТЕЛЯ&oldAge=ВОЗРАСТ_ПОЛЬЗОВАТЕЛЯ&oldIsMarried=ЗНАЧЕНИЕ_TRUE_ИЛИ_FALSE &newName=НОВОЕ_ИМЯ_ПОЛЬЗОВАТЕЛЯ&newAge=НОВЫЙ_ВОЗРАСТ_ПОЛЬЗОВАТЕЛЯ&newIsMarried=НОВОЕ_ЗНАЧЕНИЕ_TRUE_ИЛИ_FALSE</strong>
            - для обновления данных конкретного пользователя</li>
    </ul>
    <ul>
        <li>Для удаления пользователя: DELETE запрос на <strong>${pageContext.request.getContextPath()}/user?name=ИМЯ_ПОЛЬЗОВАТЕЛЯ&age=ВОЗРАСТ_ПОЛЬЗОВАТЕЛЯ&isMarried=ЗНАЧЕНИЕ_TRUE_ИЛИ_FALSE</strong>
            - для удаления конкретного пользователя</li>
        <li>Для удаления пользоватей: DELETE запрос <strong>${pageContext.request.getContextPath()}/user</strong>
            - для удаления всех пользователей</li>
    </ul>
</body>
</html>
