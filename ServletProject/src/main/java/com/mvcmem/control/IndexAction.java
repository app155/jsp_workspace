package com.mvcmem.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcmem.action.Action;

public class IndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return new ActionForward("/mvcmem/index.jsp", false);
	}

}
