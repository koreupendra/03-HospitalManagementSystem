<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="in.upendra.user" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
</head>
<body>
    <h1>Edit User Information</h1>
    
    <form action="editRecord" method="doPost1">
     
   <input type="hidden" name="id" value="${editData.id}"><br/>
   Name:<input type="text" name="name" value="${editData.name}"><br/>
    Email:<input type="email" name="email" value="${editData.email}"><br/>
     phone:<input type="number" name="phno" value="${editData.phno}"><br/>
     <input type="submit" value="update User">
   
  
    </form>
</body>
</html>
