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
		    				<button ng-if="file.hasSubtitle" ng-click="showAddSubModal(file)" type="button"><i class="material-icons">note_add</i></button>
		    				
		    			</div>
	    			</div>
	    		</div>
	    	</c:forEach>
	    </div>
	  </div>
	</div>   
	
	
	<div class="modal fade" id="addSubtitleModal" tabindex="-1" role="dialog" aria-labelledby="addSubtitleModal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Add Subtitle Process</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div class="add-sub-title">{{addSubFile.name}}</div>
	        <select ng-model="addSubLang" class="lenguage-select">
	        	<option value="per">Persian</option>
	        	<option value="en">English</option>
	        	<option value="ger">German</option>
	        </select>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" ng-click="addSub()">Add</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<form id="search-sub-form" method="post" action="https://subscene.com/subtitles/searchbytitle" target="_blank">
		<input type="hidden" name="query" id="query" value="">
		<input type="hidden" name="l" id="l" class="text" value="">
	</form>
</div>