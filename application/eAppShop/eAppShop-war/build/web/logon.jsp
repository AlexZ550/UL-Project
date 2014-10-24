<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/app" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <link rel="shortcut icon" href="lib/favicon.ico">        
        <title>Shop Project</title>
        <bootstrap:styles />
	<app:styles />
        <bootstrap:scripts />
    </head>
    <body>
        <app:navbar />
        <div class="container">
            <h2>Login</h2>
            <form role="form" action="j_security_check" method="POST">
                <div class="form-group">
                    <label for="userName">User Name:</label>
                    <input type="text" class="form-control" id="userName" name="j_username" placeholder="Enter user name">
                </div>
                <div class="form-group">
                    <label for="userPassword">Password:</label>
                    <input type="password" class="form-control" id="userPassword" name="j_password" placeholder="Enter password">
                </div>
                <button type="submit" class="btn btn-default">Sign in</button>
            </form>
        </div>
    </body>
</html>

