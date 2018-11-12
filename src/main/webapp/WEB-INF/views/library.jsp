<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "sec" uri="http://www.springframework.org/security/tags" %>

<head>
<style> <%@include file="/WEB-INF/css/myStyle.css"%> </style>


<h2> <center> LIBRARY </center> </h2>

</head>

<html>

<body>

<a href="${pageContext.request.contextPath}/logout">logout</a>

<hr>


<h3>Available books:</h3>



<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Language</th>
    </tr>
    <c:forEach items="${books}" var="currentListItem">
        <c:url var="borrowLink" value="borrowBook">

            <c:param name="bookId" value="${currentListItem.bookId}" />
        </c:url>
        <tr>
            <td> <c:out value="${currentListItem.bookTitle}"/> </td>
            <td> <c:out value="${currentListItem.authorLastname} ${currentListItem.authorFirstname}"/> </td>
            <td> <c:out value="${currentListItem.bookLanguage}"/> </td>
            <c:if test = "${currentListItem.bookStatus == 0}">
                <td><a href="${borrowLink}">Borrow</a></td>
            </c:if>
            <c:if test = "${currentListItem.bookStatus != 0}">
                <td>Unavailable</td>
            </c:if>
        </tr>
    </c:forEach>
</table>



</body>

</html>
