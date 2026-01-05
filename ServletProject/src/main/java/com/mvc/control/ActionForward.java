package com.mvc.control;

/*
 * 	ActionForward
 * 		- 사용자의 요청을 처리할 비즈니스 로직이 구현된 XXXXXAction 객체의 생성 담당
 */

public class ActionForward {
	private String url;
	private boolean redirect;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public ActionForward() {
		
	}
	
	public ActionForward(String url) {
		this.url = url;
	}
	
	public ActionForward(String url, boolean redirect) {
		this.url = url;
		this.redirect = redirect;
	}
}
