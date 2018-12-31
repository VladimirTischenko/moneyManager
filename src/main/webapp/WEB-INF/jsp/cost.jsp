<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cost</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>${param.action == 'create' ? 'Create cost' : 'Edit cost'}</h2>
    <hr>
    <jsp:useBean id="cost" type="moneyManager.model.Cost" scope="request"/>
    <form method="post" action="costs">
        <input type="hidden" name="id" value="${cost.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${cost.dateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${cost.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="number" value="${cost.price}" name="price"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
