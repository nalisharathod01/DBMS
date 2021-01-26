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
						<li style="margin-left:-10px; font-size:0.8em">
						<form action="${pageContext.request.contextPath}/search" method="get" class="search-form">
							<input name="question" type="text" placeholder="Search question">
							<input type="submit" value="Search">
						</form>
						<!-- <a href="${pageContext.request.contextPath}/search"><i class="fa fa-search" aria-hidden="true""></i></a></li> -->
					<% } %>
                    <% if (session.getAttribute("username") != null) { %>
					    	<li>Welcome, <%
		                    String name=(String)session.getAttribute("username");  
		                    out.print(" "+name); %></li>
		                    <li><a href="logout">Logout</a></li>
		                    
					<% } else {%>
					    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                    	<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
					<% } %>

                    <% if (session.getAttribute("admin") != null) { %>
					    	<li><a class="database" href="${pageContext.request.contextPath}/initialize-database">Initialize Database</a></li>
					<% } %>
					
					
                </ul>
            </div>
        </div>
    </header>
  	<br/>
    <div class="question-section" style="padding:1em 0">
    	<h1>Common Questions</h1> <br>
        <c:if test="${empty commonQuestions}">
        	<h3>There are no questions in common between User ID: <% out.print((int)session.getAttribute("user1")); %> and 
        	User ID: <% out.print((int)session.getAttribute("user2")); %> 
        	</h3>
        </c:if>

        <c:forEach items="${commonQuestions}" var="questions">
	        <div class="question">
	            <a href="${pageContext.request.contextPath}/question?id=${questions.id}">${questions.question}</a>
	            <p>Tags <i class="fa fa-tags" aria-hidden="true"></i> : ${questions.tags}</p>
	            <a class="add-video" href="${pageContext.request.contextPath}/add-video?question=${questions.id}">Post Video</a>
	        </div>
    	</c:forEach>

    </div>

</body>
</html>