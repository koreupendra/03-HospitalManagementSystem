<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        table {
            border-collapse: collapse;
            width: 70%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
    </style>
    <title>Search users</title>
</head>
<body>
    <h1>Search users</h1>
    <form action="user" method="get">
        User ID: <input type="number" name="id">
        <input type="submit" value="Search">
    </form>

    <h1>User Search Result</h1>

    <c:choose>
        <c:when test="${user ne null}">
            <table>
                <tr>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>User Email</th>
                    <th>User Phone</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${user}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                        <td>${u.email}</td>
                        <td>${u.phno}</td>
                        <td>
                            <a href="editRecord?id=${u.id}">Edit</a> &nbsp;&nbsp;&nbsp;
                            <a href="delete?id=${u.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${not empty use}">
                    <table>
                        <tr>
                            <th>User ID</th>
                            <th>User Name</th>
                            <th>User Email</th>
                            <th>User Phone</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${use}" var="p">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.name}</td>
                                <td>${p.email}</td>
                                <td>${p.phno}</td>
                                <td>
                                    <a href="editRecord?id=${p.id}">Edit</a> &nbsp;&nbsp;&nbsp;
                                    <a href="delect?id=<c:out value='${p.id}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No users found.</p>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>
