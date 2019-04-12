<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://moneymanager/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/costDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><spring:message code="costs.title"/></h3>

            <div class="view-box">
                <div class="row">
                    <div class="col-sm-7">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form class="form-horizontal" id="filter">
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="startDate"><spring:message
                                                code="costs.startDate"/>:</label>

                                        <div class="col-sm-4">
                                            <input class="form-control" type="date" name="startDate" id="startDate">
                                        </div>

                                        <label class="control-label col-sm-3" for="startTime"><spring:message
                                                code="costs.startTime"/>:</label>

                                        <div class="col-sm-3">
                                            <input class="form-control" type="time" name="startTime" id="startTime">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="endDate"><spring:message
                                                code="costs.endDate"/>:</label>

                                        <div class="col-sm-4">
                                            <input class="form-control" type="date" name="endDate" id="endDate">
                                        </div>

                                        <label class="control-label col-sm-3" for="endTime"><spring:message
                                                code="costs.endTime"/>:</label>

                                        <div class="col-sm-3">
                                            <input class="form-control" type="time" name="endTime" id="endTime">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="panel-footer text-right">
                                <a class="btn btn-danger" type="button" onclick="clearFilter()">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-primary" type="button" onclick="updateTable()">
                                    <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="btn btn-info" onclick="add()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </a>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><spring:message code="costs.dateTime"/></th>
                        <th><spring:message code="costs.description"/></th>
                        <th><spring:message code="costs.price"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${costs}" var="cost">
                        <jsp:useBean id="cost" scope="page" type="moneyManager.to.CostWithExceed"/>
                        <tr class="${cost.exceed ? 'exceeded' : 'normal'}">
                            <td>
                                    <%--<fmt:parseDate value="${cost.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>--%>
                                    <%--<fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" />--%>
                                    ${fn:formatDateTime(cost.dateTime)}
                            </td>
                            <td>${cost.description}</td>
                            <td>${cost.price}</td>
                            <td><a class="btn btn-xs btn-primary">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a></td>
                            <td><a class="btn btn-xs btn-danger" onclick="deleteRow(${cost.id})">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"><spring:message code="costs.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3"><spring:message
                                code="costs.dateTime"/></label>

                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="dateTime" name="dateTime"
                                   placeholder="<spring:message code="costs.dateTime"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><spring:message
                                code="costs.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="<spring:message code="costs.description"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="control-label col-xs-3"><spring:message
                                code="costs.price"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="price" name="price" placeholder="1000">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>