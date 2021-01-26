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
                	<c:if test="${username != null}">
					    <li>Welcome, <%
		                    String name=(String)session.getAttribute("username");  
		                    out.print(" "+name); %></li>
		                    <li><a href="/login">Logout</a></li>
					</c:if>
                    <c:if test="${username == null}">
                    	<li><a href="login">Login</a></li>
                    	<li><a href="register">Register</a></li>
                    </c:if>
                    
                    
                    
                    <c:if test="${admin != null}">
                    	<li><a class="database" href="${pageContext.request.contextPath}/initialize-database">Initialize Database</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </header>
    <div class="form-section">
        <h1>Log In</h1>
        <form action="${pageContext.request.contextPath}/login" method="post">
        	<c:if test="${not empty error}">
			   <c:out value="${error}"/>
			</c:if>
            <input type="text" name="email" id="email" required placeholder="Enter email">
            <input type="password" name="password" id="password" required placeholder="Enter password">
            <input type="submit" value="Log In">
            
        </form>
    </div>
</body>
</html>