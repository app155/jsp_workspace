package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample/Sport")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post 방식 한글 처리
		request.setCharacterEncoding("utf-8");
		
		String[] sports = request.getParameterValues("sports");
		String gender = request.getParameter("gender");
		
		response.setContentType("text/html; charset = utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>운동</title></head><body>");
		
		for (String sport : sports) {
			out.println("좋아하는 운동: " + sport + "<br>");
		}
		
		out.println("성별: " + gender + "<br>");
		out.println("</body></html>");
	}

}
