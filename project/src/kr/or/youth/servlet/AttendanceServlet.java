package kr.or.youth.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.service.ILoginService;
import kr.or.youth.login.service.LoginServiceImpl;
import kr.or.youth.login.vo.MemberVO;
import kr.or.youth.main.service.IMainService;
import kr.or.youth.main.service.MainServiceImpl;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;

import java.io.PrintWriter;
import java.util.List;

public class AttendanceServlet extends HttpServlet{
	
	  private static final long serialVersionUID = 1L;
      
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public AttendanceServlet() {
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
	    	System.out.println("아작스 서버에서의 loginMember : " + loginMember);
	    	
	    	// 화면에서 보낸 시간 받기
	    	String loginDate = "";
	    	String dateFormat = request.getParameter("dateFormat");
	    	if(loginMember.getMemAttendance() != null) {
	    		loginDate = loginMember.getMemAttendance().substring(0, 10);
	    	}
	    	System.out.println("dateFormat : " + dateFormat);
	    	System.out.println("loginDate : " + loginDate);
	    	
	    	// 서비스딴 열기
	    	ILoginService loginService = new LoginServiceImpl();
			try {
				// 화면 날짜와 멤버의 출석 날짜가 같으면
				if(loginDate.equals(dateFormat)) {
					System.out.println("~~~~~~~~~~~loginDate dateFormat 둘이 똑같다!!!!!!!");
					throw new BizNotEffectedException();
				}
				loginService.updatePoint(loginMember.getMemId());
				loginMember = loginService.getMember(loginMember.getMemId());
				// 세션에 다시 집어넣기
				session.setAttribute("loginMember", loginMember);
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
				return;
			}
			
			// 배열로 받음
			out.print("success"); //response   
	    }

}
