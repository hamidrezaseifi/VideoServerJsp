<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		    

<div class="row main-panel">
  <div class="col-3 folder-panel">
    <div class="list-group" id="list-tab" role="tablist">
    	<c:forEach items="${folders}" var="folder" varStatus="loop">
    		<a class="list-group-item list-group-item-action <c:if test = "${loop.index == 0}" >active</c:if>" id="folder${folder.id}" data-toggle="list" href="#files-${folder.id}" role="tab" aria-controls="home">${folder.name}</a>
    	</c:forEach>
    </div>
  </div>
  <div class="col-9 files-panel">
    <div class="tab-content" id="nav-tabContent">
    	<c:forEach items="${folders}" var="folder" varStatus="loop">
    		<div class="tab-pane fade <c:if test = "${loop.index == 0}" >show active</c:if>" id="files-${folder.id}" role="tabpanel" aria-labelledby="folder${folder.id}">${folder.path}</div>
    	</c:forEach>
    </div>
  </div>
</div>   