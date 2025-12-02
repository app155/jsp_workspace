package com.test;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycle() {
    	System.out.println("LifeCycle의 생성자 호출");
    }

	public void init() throws ServletException {
		System.out.println("Init 호출");
	}

	public void destroy() {
		System.out.println("Destroy 호출");
	}
	
	// 클라이언트의 연결 요청이 있을 때 마다 호출됨
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Service 호출");
	}

}
