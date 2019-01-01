<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://moneymanager/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><fmt:message key="costs.title"/></h3>

    <form method="post" action="costs/filter">
        <dl>
            <dt><fmt:message key="costs.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="costs.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="costs.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="costs.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><fmt:message key="costs.filter"/></button>
    </form>
    <hr>
    <a href="costs/create"><fmt:message key="costs.add"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="costs.dateTime"/></th>
            <th><fmt:message key="costs.description"/></th>
            <th><fmt:message key="costs.price"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${costs}" var="cost">
            <jsp:useBean id="cost" scope="page" type="moneyManager.to.CostWithExceed"/>
            <tr class="${cost.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${cost.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(cost.getDateTime())%>--%>
                        ${fn:formatDateTime(cost.dateTime)}
                </td>
                <td>${cost.description}</td>
                <td>${cost.price}</td>
                <td><a href="costs/update?id=${cost.id}"><fmt:message key="common.update"/></a></td>
                <td><a href="costs/delete?id=${cost.id}"><fmt:message key="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>