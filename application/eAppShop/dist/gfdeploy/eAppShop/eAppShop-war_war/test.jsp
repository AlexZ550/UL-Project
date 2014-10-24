<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ taglib prefix="angular" tagdir="/WEB-INF/tags/angular" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/app" %>


<!DOCTYPE html>
<html ng-app="shopApp1">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <bootstrap:styles />
	<app:styles />
        <bootstrap:scripts />
        <angular:scripts />
    </head>
    <body>
        <h1>Hello World! {{1 + 2}}</h1>
    </body>
</html>
