<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		    
	<div class="subtitle-list-container">
	
		<table class="table table-bordered">
		  <thead>
		    <tr>
		      <th scope="col" class="index">#</th>
		      <th scope="col">Path</th>
		      <th scope="col">Url</th>
		      <th scope="col" class="action"><a class="action-header" href="/subtitles/create"><i class="material-icons">create_new_folder</i></a></th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${subtitles}" var="subtitle" varStatus="loop">
		    <tr>
		      <th>${loop.index + 1}</th>
		      <td>${subtitle.path}</td>
		      <td>${subtitle.suburl}</td>
		      <td>
				<a class="action" href="/subtitles/edit/${subtitle.id}"><i class="material-icons">edit</i></a>
				<a class="action" href="/subtitles/delete/${subtitle.id}"><i class="material-icons">delete</i></a>
			  </td>
		    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	
	</div>		    
   