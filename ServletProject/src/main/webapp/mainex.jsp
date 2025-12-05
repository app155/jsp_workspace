<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./resource/css/bootstrap.min.css" rel="stylesheet">
<title>Welcome</title>
</head>
<body>
	<div class="container py-4">
		<%!
			String greeting = "Welcome to MY Home";
			String tagline = "Welcome!";
		%>
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold"><%= greeting %></h1>
				<p class="col-md-8 fs-4">MY HOME</p>
			</div>
		</div>
		<div class="row align-items-md-stretch text-center">
			<div class="col-md-12">
				<div class="h-100 p-5">
					<h3><%= tagline %></h3>
					<%
						Date day = new Date();
						String am_pm;
						int hour = day.getHours();
						int minute = day.getMinutes();
						int second = day.getSeconds();
						
						if (hour / 12 == 0) {
							am_pm = "AM";
						}
						else {
							am_pm = "PM";
							hour -= 12;
						}
						
						String CT = hour + ":" + minute + ":" + second + am_pm;
						out.println("현재 접속시간: " + CT + "\n");
					%>
				</div>
			</div>
		</div>
		<%-- <h1><%= greeting %></h1>
		<h3><%= tagline %></h3> --%>
	</div>
</body>
</html>