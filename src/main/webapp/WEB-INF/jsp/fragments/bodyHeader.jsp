<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<fmt:setBundle basename="messages.app"/>
<%--<div class="header">--%>
    <%--<div class="container">--%>
        <%--<fmt:message key="users.title"/>--%>
    <%--</div>--%>
<%--</div>--%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="meal">
            <fmt:message key="app.title"/>
        </a>

        <div class="collapse navbar-collapse">
            <form:form class="navbar-form navbar-right" method="post">
                <a class="btn btn-info" role="button" href="users"><fmt:message key="users.title"/></a>
                <a class="btn btn-info" role="button" href="profile">${user.getName()} profile</a>
            </form:form>
        </div>
    </div>
</div>
