<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		
		
	<div class="form-container-parent" ng-controller="FolderController">
		<div class="form-container">
			<form method="post" action="/folders/postedit" class="needs-validation" id="main-form" novalidate>
				<input type="hidden" name="folderid" value="${folder.id}">
				<div class="form-group">
				    <label for="foldername">Folder Name</label>
				    <input type="text" class="form-control" id="foldername" name="foldername" required value="${folder.name}" placeholder="Enter Folder Name">
				    <div class="invalid-feedback">Folder Name can'nt be empty.</div>
				</div>
				<div class="form-group">
				    <label for="folderpath">Folder Path</label>
				    <input type="text" class="form-control" id="folderpath" name="folderpath" required value="${folder.path}" placeholder="Enter Folder Path">
				    <div class="invalid-feedback">Folder Path can'nt be empty.</div>
				</div>
				<div class="form-group">
				    <label >Status</label><br>
				    
					<div class="custom-control custom-radio custom-control-inline">
						<input <c:if test = "${folder.state == 1}" >checked</c:if> type="radio" id="statusactive" name="state" value="1" class="custom-control-input">
						<label class="custom-control-label" for="statusactive">Active</label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
						<input <c:if test = "${folder.state == 0}" >checked</c:if> type="radio" id="statusdisable" name="state" value="0" class="custom-control-input">
						<label class="custom-control-label" for="statusdisable">Disabled</label>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button> &nbsp; &nbsp;
				<a type="submit" class="btn btn-secondary" href="/folders">Cancel</a>
				<div class="form-message-error">${msg}</div>
			</form>
		
		</div>		
	</div>		
		    
   