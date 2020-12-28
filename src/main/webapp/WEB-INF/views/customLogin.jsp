<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2020-12-25
  Time: 오전 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<H1>Custom Login Page</H1>
<form method="post" action="/login">
    <div>
        <input type="text" name="username" value="admin"/>
    </div>
    <div>
        <input type="password" name="password" value="admin"/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>
