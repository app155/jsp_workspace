package com.mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.control.ActionForward;

/*
 * 	Action
 * 		- 모든 XXXAction 클래스가 구현할 인터페이스
 * 		  비즈니스 로직을 재정의 할 메소드를 선언
 */

public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
