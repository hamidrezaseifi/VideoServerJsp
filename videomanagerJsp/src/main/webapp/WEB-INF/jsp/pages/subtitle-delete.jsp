<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		
		
	<div class="form-container-parent">
		<div class="form-container">
			<form method="post" action="/subtitles/postdelete" class="needs-validation" id="main-form" novalidate>
				<input type="hidden" name="folderid" value="${subtitle.id}">
				<h4>Are you sure to delete this subtitle url? </h4>
				<div>${subtitle.path} :</div>
				<div>${subtitle.suburl} </div>
				<br><br>
				<button type="submit" class="btn btn-danger">Delete</button> &nbsp; &nbsp; 
				<a type="submit" class="btn btn-secondary" href="/subtitles">Cancel</a>
			</form>
		
		</div>		
	</div>		
		    
   