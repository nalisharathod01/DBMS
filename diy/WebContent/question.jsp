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
					    	<li><a class="database" href="/initialize-database">Initialize Database</a></li>
					<% } %>
					
                </ul>
            </div>
        </div>
    </header>
    <hr>
    <div class="question-section">
        <h1 class="video-question">Question</h1>
        <div class="question">
            <a>${question.question}</a>
            <p>Posted on: ${question.getDateString()}</p>
            <p>Tags <i class="fa fa-tags" aria-hidden="true"></i> : ${question.tags}</p>
            <a class="add-video" href="${pageContext.request.contextPath}/add-video?question=${question.id}">Post Video</a>
        </div>
        <h3>Videos</h3>
        <c:forEach items="${videos}" var="videos">
	        <div class="question">
	            <a href="${pageContext.request.contextPath}/video?y_id=${videos.id}&q_id=${videos.q_id}">${videos.title} - Watch Now</a>
	            
	            <h4>Description</h4>
	            <p>${videos.description}</p>
	        </div>
    	</c:forEach>
    </div>
</body>
</html>