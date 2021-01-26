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
		<h2>Excellent Reviews</h2>
		<c:forEach items="${excellentReviews}" var="review">
	        <div class="question">
	            
	            <p>User ID :${review.getY_id()}</p>
	            <p>Score :${review.getScore()}</p>
	            <p>Remark :${review.getRemark()}</p>
	            <p>Video Id :${review.getY_id()}</p>
	            
	        </div>
    	</c:forEach>
		
        
        <h2>Questions created today</h2>
        <c:forEach items="${createdToday}" var="questions">
	        <div class="question">
	            <a href="${pageContext.request.contextPath}/question?id=${questions.id}">${questions.question}</a>
	            <p>Tags <i class="fa fa-tags" aria-hidden="true"></i> : ${questions.tags}</p>
	            <a class="add-video" href="${pageContext.request.contextPath}/add-video?question=${questions.id}">Post Video</a>
	        </div>
    	</c:forEach>
		
		<h2>Hot Youtubes</h2>
		<c:forEach items="${hotYoutubes}" var="videos">
	        <div class="question">
				<p>Video Title : ${videos.getTitle()}</p>
	            <p>Video Description : ${videos.getDescription()}</p>
	            <p>Video Description : ${videos.getUrl()}</p>
	        </div>
    	</c:forEach>
    	
    	<h2>Top Question</h2>
 
        <div class="question">
            <a href="${pageContext.request.contextPath}/question?id=${topQuestion.id}">${topQuestion.question}</a>
            <p>Tags <i class="fa fa-tags" aria-hidden="true"></i> : ${topQuestion.tags}</p>
            <a class="add-video" href="${pageContext.request.contextPath}/add-video?question=${topQuestion.id}">Post Video</a>
        </div>
		
		<h2>Popular tags</h2>
		<c:forEach items="${popularTags}" var="tags">
	        <div class="question">
				<p>Tag : ${tags.getTagName()}</p>
	            <p>Question ID : ${tags.getQuestionId()}</p>
	        </div>
    	</c:forEach>
    	
    	<h2>Common Questions</h2>
    	<div class="question">
    		<form method="post" action="${pageContext.request.contextPath}/stats">
	    		<select name="user1">
	    			<option value="" disabled selected>Select first user</option>
	    			<c:forEach items="${getAllUsers}" var="user">
						<option value="${user.getId()}">User ID: ${user.getId()} First Name :  ${user.getFirstname()}</option>
			    	</c:forEach>
	    		</select>
	    		
	    		<select name="user2">
	    			<option value="" disabled selected>Select second user</option>
	    			<c:forEach items="${getAllUsers}" var="user">
						<option value="${user.getId()}">User ID: ${user.getId()} First Name :  ${user.getFirstname()}</option>
			    	</c:forEach>
	    		</select>
	    		
	    		<input type="submit" value="Check common question">
    		</form>
    	</div>
    	
    	<h2>Top Reviewer</h2>

        <div class="question">
			<a href="${pageContext.request.contextPath}/user-videos?user-id=${topReviewer.getId()}">${topReviewer.getFirstname()} ${topReviewer.getLastname()}</a>
            <p> Email : ${topReviewer.getEmail() } </p>
        </div>
    	
    	<h2>Positive Reviewers</h2>
		<c:forEach items="${positiveReviewers}" var="user">
	        <div class="question">
				<p>First Name : ${user.getFirstname()}</p>
	            <p>Last Name : ${user.getLastname()}</p>
	            <p> Email : ${user.getEmail() } </p>
	        </div>
    	</c:forEach>
    	
    	<h2>Poor Questions</h2>
        <c:forEach items="${poorQuestions}" var="questions">
	        <div class="question">
	            <a href="${pageContext.request.contextPath}/question?id=${questions.id}">${questions.question}</a>
	            <p>Tags <i class="fa fa-tags" aria-hidden="true"></i> : ${questions.tags}</p>
	            <a class="add-video" href="${pageContext.request.contextPath}/add-video?question=${questions.id}">Post Video</a>
	        </div>
    	</c:forEach>
    	
    	<h2>Inactive Users</h2>
		<c:forEach items="${inactiveUsers}" var="user">
	        <div class="question">
				<p>First Name : ${user.getFirstname()}</p>
	            <p>Last Name : ${user.getLastname()}</p>
	            <p> Email : ${user.getEmail() } </p>
	        </div>
    	</c:forEach>
    </div>

</body>
</html>