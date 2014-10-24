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
        <title>Shop Project : Logon Error</title>
        <bootstrap:scripts />
        <bootstrap:styles />
        <app:styles />
    </head>
    <body>
        <app:navbar />
        <div class="container">
            <c:url var="url" value="index.jsp"/>
            <h2>Invalid user name or password.</h2>

            <p>Please enter a user name or password that is authorized to access this 
                application. For this application, this means a user that has been created in the 
                <code>file</code> realm and has been assigned to the <em>group</em> of 
                <code>Customer</code> or <code>Administrator</code>.  
                Click here to <a href="index.jsp">Try Again</a></p>
        </div>
    </body>
</html>
