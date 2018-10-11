<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        FIZZBUZZ
    </title>


</head>
<body>
<c:forEach var="item" begin="1" end="30">
    <c:choose>
        <c:when test="${item%15==0}">
        <p>fizzBuzz</p>
    </c:when>
    <c:when test="${item%3==0}">
    <p>fizz</p>
    </c:when>
    <c:when test="${item%5==0}">
    <p>Buzz</p>
    </c:when>
    <c:otherwise>
        <p>${item}</p>
    </c:otherwise>
    </c:choose>
</c:forEach>
</body>
</html>