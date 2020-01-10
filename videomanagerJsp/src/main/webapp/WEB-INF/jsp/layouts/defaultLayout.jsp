<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en" ng-app="videoApp">
	<head>
	    <meta charset="UTF-8">
	    <title>Video Manager ...</title>
	    
	    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	    <link href="/resources/css/site.css" rel="stylesheet">
	    <link href="/resources/css/icons.css" rel="stylesheet">
	    <link rel="shortcut icon" type="image/x-icon" href="/resources/images/video.png">
	    
		<script src="/resources/js/jquery-3.4.1.min.js"></script>
		<script src="/resources/js/bootstrap.min.js"></script>
		<script src="/resources/js/angular.min.js"></script>
		<script src="/resources/js/index.js"></script>
	</head>
	<body ng-controller="BodyController">

		<header>
			<tiles:insertAttribute name="header" />
		</header>
		
		<div class="container maincontainer">
			<tiles:insertAttribute name="body" />
		</div>
		
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer> 
		     
	</body>
</html>
	
      
    
