<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <div class="form-section">
    	<c:if test="${not empty error}">
		   <c:out value="${error}"/>
		</c:if>
        <h1>Add video to Question - <% out.print(request.getParameter("question")); %></h1>
        <form action="" method="post">
            <input type="text" name="video-title" id="video-title" required placeholder="Enter video title">
            <input type="text" name="url" id="url" required placeholder="Enter video url">
            <textarea name="video-description" id="video-description" cols="30" rows="10" placeholder="Video Description"></textarea>
            <input type="submit" value="Add video">
        </form>
    </div>
</body>
</html>