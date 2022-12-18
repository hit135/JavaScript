package kr.or.youth.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.youth.servlet.YouthPolicyProcess;

public class LogOut implements YouthPolicyProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("LogOut process에 잘 도착!");
		
		// 세션에서 로그인한 아이디 지우기
		HttpSession session = request.getSession();
		session.removeAttribute("loginMember");
		
		return "/WEB-INF/views/main/main.jsp";
	}

}
