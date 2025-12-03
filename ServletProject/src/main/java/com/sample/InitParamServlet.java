package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InitParam", urlPatterns = {"/sample/InitParam"},
			initParams = {@WebInitParam(name = "tel", value = "010-1234-5678"),
					@WebInitParam(name = "email", value = "test@asdf.com")})
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ServletContext 초기 파라미터를 읽어와 저장할 변수
	private String company;
	private String manager;
	// ServletConfig 초기 파라미터를 읽어와 저장할 변수
	private String tel;
	private String email;

	public void init() throws ServletException {
		System.out.println("초기화 메소드 실행");
		//ServletContext 파라미터값을 읽어와 저장
		company = getServletContext().getInitParameter("company");
		manager = getServletContext().getInitParameter("manager");
		
		//ServletConfig 파라미터값을 읽어와 저장
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title> ServletContext, ServletConfig </title></head><body>");
		out.println("<ul>");
		out.println("<li> 회사명: " + company + "</li>");
		out.println("<li> 관리자: " + manager + "</li>");
		out.println("<li> 전화번호: " + tel + "</li>");
		out.println("<li> 이메일: " + email + "</li>");
		out.println("</ul>");
		out.println("</body></html>");
		
	}

}
