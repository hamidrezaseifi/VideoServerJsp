<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
</head>
<body>
    <h2 class="hello-title">Submited User:</h2>
    <hr>
    <div class="hello-title"><strong>First Name: </strong>${myuser.getFirstname()}</div>
	<br>
    <div class="hello-title"><strong>Last Name: </strong>${myuser.getLasttname()}</div>
    
    
</body>
</html>