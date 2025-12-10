<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jdbc.*" %>

<%
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	ConnectionPool pool = ConnectionPool.getInstance();
	
	String id = "", passwd = "", name = "", mem_num1 = "", mem_num2 = "", e_mail = "", phone = "", zipcode = "", address = "", job = "";
	
	int counter = 0;
	
	try {
		con = pool.getConnection();
		stmt = con.createStatement();
		String sql = "SELECT * FROM TEMPMEMBER";
		rs = stmt.executeQuery(sql);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP에서 데이터베이스 연동</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffcc">
	<h2>JSP에서 ConnectionPool을 이용한 데이터베이스 연동</h2>
	<h3>회원 정보</h3>
	<table border="1" bordercolor="#0000ff">
		<tr>
			<td><strong>ID</strong></td>
			<td><strong>PASSWD</strong></td>
			<td><strong>NAME</strong></td>
			<td><strong>MEM_NUM1</strong></td>
			<td><strong>MEM_NUM2</strong></td>
			<td><strong>E_MAIL</strong></td>
			<td><strong>PHONE</strong></td>
			<td><strong>ZIPCODE/ADDRESS</strong></td>
			<td><strong>JOB</strong></td>
		</tr>
		<%
			if (rs != null) {
				while (rs.next()) {
					id = rs.getString("id");
					passwd = rs.getString("passwd");
					name = rs.getString("name");
					mem_num1 = rs.getString("mem_num1");
					mem_num2 = rs.getString("mem_num2");
					e_mail = rs.getString("e_mail");
					phone = rs.getString("phone");
					zipcode = rs.getString("zipcode");
					address = rs.getString("address");
					job = rs.getString("job");
		%>
		<tr>
			<td><%=id %></td>
			<td><%=passwd %></td>
			<td><%=name %></td>
			<td><%=mem_num1 %></td>
			<td><%=mem_num2 %></td>
			<td><%=e_mail %></td>
			<td><%=phone %></td>
			<td><%=zipcode %>/<%=address %></td>
			<td><%=job %></td>
		</tr>
		<%
					counter++;
				}
			}
		%>
	</table>
	<br>
	
	total records: <%=counter %>
	<%
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				}
				catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				}
				catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				}
				catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
		}
	%>
</body>
</html>