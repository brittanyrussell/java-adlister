<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <jsp:include page="partials/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="partials/navbar.jsp"></jsp:include>
<h2>Please login below..</h2>
<br>
<hr>
<br>

<form action="login.jsp" method="post">
      Username: <input type="text" name="username"/>
      Password: <input type="password" name="password"/>
      <input type="submit" value="login"/>
</form>

<c:if test="${param.username.equals('admin') && param.password.equals('password')}">
    <% response.sendRedirect("profile.jsp");%>
</c:if>





</body>
</html>