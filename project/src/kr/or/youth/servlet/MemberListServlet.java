package kr.or.youth.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.vo.MemberVO;
import kr.or.youth.main.service.IMainService;
import kr.or.youth.main.service.MainServiceImpl;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;

import java.io.PrintWriter;
import java.util.List;

public class MemberListServlet extends HttpServlet{
	
	  private static final long serialVersionUID = 1L;
      
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public MemberListServlet() {
	        super();
	    }
	 
	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("MemberListServlet doGet");
			response.setContentType("application/x-json; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			// object > json
			ObjectMapper mapper = new ObjectMapper();
			IMainService mainService = new MainServiceImpl();
			//PagingVO pagingVO = new PagingVO();
			MemberVO member = new MemberVO();
			String jsonMemberList = null;
			try {
				List<MemberVO> memberList = mainService.getMemberList();
				jsonMemberList = mapper.writeValueAsString(memberList);
				System.out.println("jsonMemberList : " + jsonMemberList);
				// request에 담기
				request.setAttribute("jsonMemberList", jsonMemberList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 배열로 받음
			out.print(jsonMemberList); //response   
	    }
	 
}
