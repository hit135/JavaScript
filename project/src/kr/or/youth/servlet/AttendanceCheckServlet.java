package kr.or.youth.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.or.youth.login.service.ILoginService;
import kr.or.youth.login.service.LoginServiceImpl;
import kr.or.youth.login.vo.MemberVO;

import java.io.PrintWriter;

public class AttendanceCheckServlet extends HttpServlet{
	
	  private static final long serialVersionUID = 1L;
      
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public AttendanceCheckServlet() {
	        super();
	    }
	 
	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	// 처음 설정
	    	response.setContentType("application/x-json; charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	
	    	// 세션에서 아이디 얻기
	    	HttpSession session = request.getSession();
	    	MemberVO loginMember = (MemberVO)session.getAttribute("loginMember");
	    	System.out.println("서블릿에서 loginMember : " + loginMember);
	    	// 리턴할 모든 날짜
	    	String MemAttendDate = "";
	    	// 서비스딴 열기
	    	ILoginService loginService = new LoginServiceImpl();
			try {
				// mem_sysDate에서 시간들 받기(MM-DD,MM-DD,MM-DD)
				MemAttendDate = loginService.getMemAttendDate(loginMember.getMemId());
				System.out.println("서블릿으로 가져온 MemAttendDate : " + MemAttendDate);
			} catch (Exception e) {
				e.printStackTrace();
				out.print("none");
			}
			
			out.print(MemAttendDate); //response   
	    }

}
