package com.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.CommandAction;


// @WebServlet("/ControllerAction")
public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 명령어와 명령어 처리 클래스를 쌍으로 저장
	private Map<String, Object> commandMap = new HashMap<>();
		
	// 명령어, 처리 클래스가 매핑된 properties 파일을 읽어와 저장함.
		
	
	public void init(ServletConfig config) throws ServletException {
		// web.xml에서 propertyConfig에 해당하는 init-param값을 읽어옴.
		String props = config.getInitParameter("propertyConfig");
		// 명령어, 처리클래스의 매핑정보를 저장할 Properties객체 생성
		Properties pr = new Properties();
			
		String path = config.getServletContext().getRealPath("/WEB-INF");
				
		FileInputStream f = null;
				
		try {
			// Command.properties의 내용을 읽어오기
			f = new FileInputStream(new File(path, props));
			pr.load(f);
		} 
		catch (IOException e) {
			throw new ServletException(e);
		}
		finally {
			if (f != null) {
				try {
					f.close();
				}
				catch (IOException e) {
							
				}
			}
		}
				
		Iterator<Object> keyIter = pr.keySet().iterator();
			
		while (keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
				// 해당 문자열을 클래스로 만듬
				Class commandClass = Class.forName(className);
				
				// 해당 클래스의 객체 생성
				Object commandInstance = commandClass.newInstance();
						
				// Map에 객체 저장
				commandMap.put(command, commandInstance);
			} 
			catch (ClassNotFoundException fe) {
				throw new ServletException(fe);
			}
			catch (InstantiationException ie) {
				throw new ServletException(ie);
			}
			catch (IllegalAccessException iae) {
				throw new ServletException(iae);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		
		try {
			String command = request.getRequestURI();
			
			if (command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}
		catch (Throwable e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
