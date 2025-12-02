package com.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/sample/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Enumeration<String> enu = request.getParameterNames();
		
		response.setContentType("text/html; charset = utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>회원가입</title></head><body>");
		
		while (enu.hasMoreElements()) {
			String name = enu.nextElement();
			String value = request.getParameter(name);
			out.println(name + ": " + value + "<br>");
		}
		
		out.println("</body></html>");
	}

}
