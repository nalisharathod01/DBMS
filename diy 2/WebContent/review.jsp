<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Do It Yourself - Video Website</title>
    <style><%@ include file="css/style.css" %></style>   
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
</head>
<body>
    <header>
        <div class="flex-container nav">
            <div class="nav-left">
                <a href="${pageContext.request.contextPath}/">
                    DIY-Videos
                </a>
            </div>
            <div class="nav-right">
                <ul>
                    <% if (session.getAttribute("username") != null) { %>
					    	<li>Welcome, <%
		                    String name=(String)session.getAttribute("username");  
		                    out.print(" "+name); %></li>
		                    <li><a href="logout">Logout</a></li>
					<% } else {%>
					    <li><a href="login.html">Login</a></li>
                    	<li><a href="register.html">Register</a></li>
					<% } %>

                    <% if (session.getAttribute("admin") != null) { %>
					    	<li><a class="database" href=""${pageContext.request.contextPath}/initialize-database"">Initialize Database</a></li>
					<% } %>
                </ul>
            </div>
        </div>
    </header>
    <div class="form-section">
        <h1>Review Video ----</h1>
        <form action="${pageContext.request.contextPath}/review" method="post">
            <select name="review" id="review" required> 
            	<c:if test="${isEditing}"><p>Current Score</p>${review.score}</c:if>
            	
                <option value="Choose Score">Choose Score</option>
                <option value="poor">Poor</option>
                <option value="fair">Fair</option>
                <option value="good">Good</option>
                <option value="excellent">Excellent</option>
            </select>
            <input type="text" name="y_id" hidden value="${y_id }" />
            <c:if test="${!isEditing}"><input type="text" name="Remark" id="Remark" required placeholder="Enter Remark"></c:if>
            <c:if test="${isEditing}"><input type="text" name="Remark" id="Remark" value="${review.remark}"></c:if>
            
            <c:if test="${!isEditing}"><input type="submit" value="Submit Review"></c:if>
            <c:if test="${isEditing}"><input type="submit" value="Edit Review"></c:if>

        </form>
    </div>
</body>
</html>