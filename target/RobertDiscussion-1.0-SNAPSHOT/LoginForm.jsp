<%-- 
    Document   : LoginForm
    Created on : Mar 8, 2015, 3:21:36 PM
    Author     : Robert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login:</h1>
        <form action="LoginChecker" method="POST">
            Username:<input type="text" name="username" /><br/>
            Password:<input type="password" name="pass" /><br/>
            <input type="submit" value="Go!"/><br/>
        </form>
    </body>
</html>
