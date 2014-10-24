<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ taglib prefix="angular" tagdir="/WEB-INF/tags/angular" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/app" %>

<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="shopApp">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <link rel="shortcut icon" href="lib/favicon.ico">        
        <title>Shop Project</title>
        <bootstrap:styles />
	<app:styles />
        <bootstrap:scripts />
        <angular:scripts />
    </head>
    <body>
        <app:navbar />
        <div growl></div>
	<div class="container">
	   <div ng-view class="view-frame"></div>
	</div>
    </body>
</html>
