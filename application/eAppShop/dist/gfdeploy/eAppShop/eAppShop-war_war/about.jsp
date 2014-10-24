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
        <title>Shop Project : About</title>
        <bootstrap:scripts />
        <bootstrap:styles />
        <app:styles />
    </head>
    <body>
        <app:navbar />
        <div class="container">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-10">
                        <h4>ET4437 Programming project</h4>
                        <strong>Students:</strong>
                        <ul>
                            <li>Paul MacDarby</li>
                            <li>Galina Mischenko</li>
                            <li>Alexey Boyko</li>
                        </ul>
                        <p>Please click here to return to <a href="index.jsp" onclick="$('#home-link').trigger('click');"> <em>Product list</em></a></p>        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
