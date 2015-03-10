<%-- 
    Document   : HomePage
    Created on : Mar 8, 2015, 3:32:04 PM
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
        <h3>Welcome to the discussion forum ${username}.</h3>
        <form action="AddPost" method="POST">
            <a href="PostPage.jsp">View Posts</a><br/>
            or<br/>
            Submit a post:<br/>
            <textarea rows="4" cols="50" name="postform"></textarea>
            <input type="submit" value="Post"/><br/>
        </form>
        <a href="LoginForm.jsp">Logout</a>
    </body>
</html>