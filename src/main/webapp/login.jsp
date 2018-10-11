<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Please login below..</h2>
<br>
<hr>
<br>

<form action="login.jsp" method="post">
      Username: <input type="text" name="username"/><br><br>
      Password: <input type="password" name="password"/><br><br>
      <input type="submit" value="login"/>
</form>


<c:if test="${param.username.equals('admin') && param.password.equals('password')}">
    <% response.sendRedirect("profile.jsp");%>
</c:if>





</body>
</html>