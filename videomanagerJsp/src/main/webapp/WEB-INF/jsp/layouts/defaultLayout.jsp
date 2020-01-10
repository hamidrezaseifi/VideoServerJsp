<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<tiles:importAttribute name="jsfilesList"/>
<tiles:importAttribute name="cssfilesList"/>


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
		
		<c:forEach var="jsfileName" items="${jsfilesList}">
		    <script src="<%=request.getContextPath()%><c:out value='${jsfileName}' />"></script>
		</c:forEach>
		<c:forEach var="cssfileName" items="${cssfilesList}">
		    <link href="<%=request.getContextPath()%><c:out value='${cssfileName}' />" rel="stylesheet">
		</c:forEach>
		
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
	
      
    
