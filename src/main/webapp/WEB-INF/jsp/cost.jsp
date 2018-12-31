<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="cost" type="moneyManager.model.Cost" scope="request"/>
    <h3><fmt:message key="${cost.isNew() ? 'costs.add' : 'costs.edit'}"/></h3>
    <hr>
    <form method="post" action="costs">
        <input type="hidden" name="id" value="${cost.id}">
        <dl>
            <dt><fmt:message key="costs.dateTime"/>:</dt>
            <dd><input type="datetime-local" value="${cost.dateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="costs.description"/>:</dt>
            <dd><input type="text" value="${cost.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="costs.price"/>:</dt>
            <dd><input type="number" value="${cost.price}" name="price"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
