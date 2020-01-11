<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		    
<div ng-controller="IndexController">

	<div class="row main-panel">
	  <div class="col-3 folder-panel">
	    <div class="list-group" id="list-tab" role="tablist">
	    	<c:forEach items="${folders}" var="folder" varStatus="loop">
	    		<a class="list-group-item list-group-item-action <c:if test = "${loop.index == 0}" >active</c:if>" ng-click="reloadFiles(${folder.id})" id="folder${folder.id}" data-toggle="list" href="#files-${folder.id}" role="tab" aria-controls="home">${folder.name}</a>
	    	</c:forEach>
	    </div>
	  </div>
	  <div class="col-9 files-panel">
	    <div class="tab-content" id="nav-tabContent">
	    	<c:forEach items="${folders}" var="folder" varStatus="loop">
	    		<div class="tab-pane fade <c:if test = "${loop.index == 0}" >show active</c:if>" id="files-${folder.id}" role="tabpanel" aria-labelledby="folder${folder.id}">
	    			<h3>${folder.path}</h3>
	    			<div class="file-list-container">
		    			<div ng-repeat="file in getFolderFiles(${folder.id})" class="file-list-item" ng-class="{'hassub': file.converted || file.hasConverted, 'nosub' : (file.converted === false) || (file.hasConverted === false)}">
		    				<span class="filename">{{file.name}}</span>
		    				<a target="_self" href="localexplorer:{{file.folderPath}}"><i class="material-icons">folder</i></a>
		    				<button type="button" ng-click="searchSub(file.name)"><i class="material-icons">search</i></button>
		    				<button type="button"><i class="material-icons">note_add</i></button>
		    				
		    			</div>
	    			</div>
	    		</div>
	    	</c:forEach>
	    </div>
	  </div>
	</div>   
	
	<form id="search-sub-form" method="post" action="https://subscene.com/subtitles/searchbytitle" target="_blank">
		<input type="hidden" name="query" id="query" value="">
		<input type="hidden" name="l" id="l" class="text" value="">
	</form>
</div>