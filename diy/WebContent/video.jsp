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
					    	<li><a class="database" href="${pageContext.request.contextPath}/initialize-database">Initialize Database</a></li>
					<% } %>
                </ul>
            </div>
        </div>
    </header>
    <hr>
    <div class="video-section">
        <h1>Title: ${video.title }</h1>
        <a href="${video.getUrl() }" target="_blank">Not viewable? Click to watch in youtube</a>
        <div class="flex-container" style="padding-top:1em !important;">
            <div class="video-column">
                <iframe width="700" height="400" src="${video.url}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe> <br>
                <br/>
                <c:if test="${!isFavorite}">
			         <a href="${pageContext.request.contextPath}/favorite?q_id=${question.id}&y_id=${video.id }&isTrue=${isFavorite}" style="color:rgb(80, 80, 80); padding: 1.5em 0;">Add to favorite <i class="fa fa-heart" aria-hidden="true"></i></a>
			    </c:if>
			    <c:if test="${isFavorite}">
			         <a href="${pageContext.request.contextPath}/favorite?q_id=${question.id}&y_id=${video.id }&isTrue=${isFavorite}" style="color:rgb(80, 80, 80); padding: 1.5em 0;">Remove to favorite <i style="color:red;" class="fa fa-heart" aria-hidden="true"></i></a> <br> 
			    </c:if>
                 
                <br/>
                <h3 style="padding-top:1em;">Description</h3>
                <p>${video.description }</p>
            </div>
            <div class="question-column">
                <h2>Question</h2>
                <div class="question">
                    <a href="${pageContext.request.contextPath}/question?id=${question.id}">${question.question }</a>
                    <p>Tags <i class="fa fa-tags" aria-hidden="true"></i> : ${question.tags} </p>
                </div>
                <h2>Review</h2>
                <c:if test="${isReview}">
	                <div class="question">
	                    <a class="add-video" href="${pageContext.request.contextPath}/review?y_id=${video.id}&edit=true">Edit Review</a>
	                    
	                </div>
	            </c:if>
	            <c:if test="${!isReview}">
	                <div class="question">
	                	
	                	<% if ((int)session.getAttribute("user_id") == (int)session.getAttribute("userPosted")) { %>
	                		<p>You cannot review your own video</p>
	                	<%} else {%>
	                		<a class="add-video" href="${pageContext.request.contextPath}/review?y_id=${video.id}">Post Review</a>
	                	<% } %>
	                    
	                </div>
	            </c:if>
            </div>
        </div>
    </div>
</body>
</html>