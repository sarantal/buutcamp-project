<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <body>

        <h3>Registration Page</h3>
        <hr>

        <form:form action="${pageContext.request.contextPath}/processRegister"
            modelAttribute="myuser" method="POST">

            <c:if test="${registrationError != null}">
                <p style="color:red;"><c:out value="${registrationError}"/></p>
            </c:if>

            <form:input path="userName"  placeholder="username" />

            <form:input path="userEmail"  placeholder="email" />

            <form:password path="password" placeholder="password" />

            <form:select path="role" items="${roles}" />

            <input type="submit" value="Submit" />

        </form:form>

    </body>

</html>