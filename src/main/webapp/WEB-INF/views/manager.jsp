<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>


<head>
<style> <%@include file="/WEB-INF/css/myStyle.css"%> </style>

<h2> <center> Library ADMIN </center> </h2>

</head>

<html>

<body>

<a href="${pageContext.request.contextPath}/logout">logout</a>

<hr>


<h3>Inventory:</h3>



<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Language</th>
    </tr>
    <c:forEach items="${books}" var="currentListItem">
        <c:url var="deleteLink" value="deleteBook">
            <c:param name="bookId" value="${currentListItem.bookId}" />
        </c:url>
        <tr>
            <td> <c:out value="${currentListItem.bookTitle}"/> </td>
            <td> <c:out value="${currentListItem.authorLastname} ${currentListItem.authorFirstname}"/> </td>
            <td> <c:out value="${currentListItem.bookLanguage}"/> </td>
            <td><a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) return false">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<hr>

<h3>Add new book</h3>
<form:form action="saveBook" modelAttribute="book" method="POST">
    <table>
        <tr>
            <td>Title:</td2>
            <td><form:input path="bookTitle" /></td>
        </tr>
        <tr>
            <td>Authors first name:</td2>
            <td><form:input path="authorFirstname" /></td>
        </tr>
        <tr>
            <td>Authors last name:</td2>
            <td><form:input path="authorLastname" /></td>
        </tr>
        <tr>
            <td>Language:</td2>
            <td><form:input path="bookLanguage" /></td>
        </tr>
        <tr>
            <td>Other information:</td2>
            <td><form:input path="bookInformation" /></td>
        </tr>
        <tr>
            <td>Image path:</td2>
            <td><form:input path="bookImage" /></td>
        </tr>
        <tr>
            <td />
            <td />
            <td><input type="submit" value="SAVE " /></td>
        </tr>
    </table>
</form:form>

<hr>



</body>

</html>
