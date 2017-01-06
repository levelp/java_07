<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Поиск по резюме</title>
</head>
<body>
<header>Поиск по резюме</header>

<form action="list.jsp" method="get">
    <label for="query">Строка для поиска:</label>
    <input type="text" name="s" id="query"/>
    <input type="submit" value="Искать!"/>
</form>

</body>
</html>
