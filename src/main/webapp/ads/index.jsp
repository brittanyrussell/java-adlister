<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: brittany
  Date: 10/12/18
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ads</title>
</head>
<body>
<h1>Here are all the ads!</h1>
<c:forEach var="Ad" items="${Ad}">
    <h4>${Ad.title}</h4>
    <h4>${Ad.description}</h4>
</c:forEach>
</body>
</html>
