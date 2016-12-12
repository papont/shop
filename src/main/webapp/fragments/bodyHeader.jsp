<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>


<fmt:setBundle basename="messages.app"/>
<div class="header">
    <div class="container">
        <fmt:message key="users.title"/>
    </div>
</div>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <a href="meal">
            <div class="navbar-header navbar-brend">
                <fmt:message key="app.title"/>
            </div>
        </a>

        <div class="collapse navbar-collapse">
            <form class="collapse navbar-collapse">
                <a class="btn btn-info" role="button" href="users"><fmt:message key="users.title"/></a>
                <a class="btn btn-info" role="button" href="profile">${user.getName()} profile</a>
            </form>
        </div>
    </div>
</div>
