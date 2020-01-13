<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		
		
	<div class="form-container-parent" ng-controller="SubtitleController">
		<div class="form-container">
			<form method="post" action="/subtitles/postcreate" class="needs-validation" id="main-form" novalidate>
				<div class="form-group">
				    <label for="foldername">Folder Path</label>
				    <input type="text" class="form-control" id="foldername" name="path" required value="" placeholder="Enter Folder Path">
				    <div class="invalid-feedback">Folder Path can'nt be empty.</div>
				</div>
				<div class="form-group">
				    <label for="folderpath">Subtitle Url</label>
				    <input type="text" class="form-control" id="folderpath" name="suburl" required value="" placeholder="Enter Subtitle Url">
				    <div class="invalid-feedback">Subtitle Url can'nt be empty.</div>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button> &nbsp; &nbsp;
				<a type="submit" class="btn btn-secondary" href="/subtitles">Cancel</a>
				<div class="form-message-error">${msg}</div>
			</form>
		
		</div>		
	</div>		
		    
   