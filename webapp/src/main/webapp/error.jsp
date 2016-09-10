<%@ page import="webapp.WebAppException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<%
    WebAppException exception = (WebAppException)
            request.getAttribute("javax.servlet.error.exception");
%>
<h2><%=exception.getMessage()%>
</h2>

<%
    exception.printStackTrace(response.getWriter());
%>
</body>
</html>