<%@ page import="java.util.*, java.sql.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		    
	<table class="table table-bordered">
	  <thead>
	    <tr>
	      <th scope="col" class="index">#</th>
	      <th scope="col">Name</th>
	      <th scope="col">Path</th>
	      <th scope="col" class="action">...</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${folders}" var="folder" varStatus="loop">
	    <tr>
	      <th>${loop.index + 1}</th>
	      <td>${folder.name}</td>
	      <td>${folder.path}</td>
	      <td>
			<a class="action" href="/folders/edit/${folder.id}"><i class="material-icons">edit</i></a>
			<a class="action" href="/folders/delete/${folder.id}"><i class="material-icons">delete</i></a>
		  </td>
	    </tr>
	    </c:forEach>
	  </tbody>
	</table>
   