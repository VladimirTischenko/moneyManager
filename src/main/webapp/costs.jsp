<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://moneymanager/functions" %>
<html>
<head>
    <title>Cost list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Cost list</h2>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        </thead>
        <c:forEach items="${costs}" var="cost">
            <jsp:useBean id="cost" scope="page" type="moneyManager.model.CostWithExceed"/>
            <tr class="${cost.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${cost.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(cost.getDateTime())%>--%>
                        ${fn:formatDateTime(cost.dateTime)}
                </td>
                <td>${cost.description}</td>
                <td>${cost.price}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>