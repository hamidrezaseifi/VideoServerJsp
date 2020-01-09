<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
</head>
<body>
    <h2 class="hello-title">Hello ${name}!</h2>
    
    <hr>
    
    <form:form method="POST" action="/postform" modelAttribute="myuser">
    <table>
          <tr>
              <td>First Name:</td>
              <td><form:input path="firstname" /></td>
          </tr>
          <tr>
              <td>Last Name:</td>
              <td><form:input path="lasttname" /></td>
          </tr>
          <tr>
              <td colspan="2">
                  <input type="submit" value="Save Changes" />
              </td>
          </tr>
      </table>
    </form:form>
</body>
</html>