<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		
		
	<div class="form-container-parent">
		<div class="form-container">
			<form method="post" action="/folders/postdelete" class="needs-validation" id="main-form" novalidate>
				<input type="hidden" name="folderid" value="${folder.id}">
				<h4>Are you sure to delete this folder? </h4>
				<div>${folder.name} :</div>
				<div>${folder.path} </div>
				<br><br>
				<button type="submit" class="btn btn-danger">Delete</button> &nbsp; &nbsp; 
				<a type="submit" class="btn btn-secondary" href="/folders">Cancel</a>
			</form>
		
		</div>		
	</div>		
		    
   