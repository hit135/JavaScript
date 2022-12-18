package kr.or.youth.login.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.youth.exception.BizDuplicateKeyException;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.service.ILoginService;
import kr.or.youth.login.service.LoginServiceImpl;
import kr.or.youth.login.vo.MemberVO;
import kr.or.youth.servlet.YouthPolicyProcess;

public class Login implements YouthPolicyProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션 사용
		HttpSession session = request.getSession();
		System.out.println("Login process에 들어옴");
		
		// 화면에 alert 띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 넘긴거 받기
		MemberVO member = new MemberVO();
		BeanUtils.populate(member, request.getParameterMap());
		System.out.println("Login process의 member : " + member);
		
		// 서비스딴으로 넘기기
		ILoginService loginService = new LoginServiceImpl();
		
		
		// 회원 정보
		MemberVO dbMember = new MemberVO();
		
		if(member.getMemName().equals("") || member.getMemName() == null) {
			// sign in
			System.out.println("sign in 잘옴!!!!!!!!!!!!!!!!!!!!!!");
			
			try {
				dbMember = loginService.getMember(member.getMemId());
				// 비번 틀리면
				if(! dbMember.getMemPass().equals(member.getMemPass()) ) {
					throw new BizDuplicateKeyException();
				}
			// 아이디 없음
			} catch (BizNotEffectedException bne) {
				bne.printStackTrace();
				out.println("<script>alert('ID가 없습니다! 회원가입을 진행해주세요!');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/main.do'; </script>");
				out.flush();
			// 비번 틀림
			} catch (BizDuplicateKeyException bde) {
				bde.printStackTrace();
				out.println("<script>alert('비밀번호가 틀립니다! 다시 로그인해주십시오!');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/main.do'; </script>");
				out.flush();
			// 그외의 오류
			} catch (Exception e) {
				out.println("<script>alert('로그인에 실패했습니다!\n 전산실에 문의해주세요 010-4403-9382');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/main.do'; </script>");
				out.flush();
				e.printStackTrace();
			}
			// 로그인 성공
			// 일단 비번 지우고
			dbMember.setMemPass("");
			System.out.println("dbMember : " + dbMember);
			// 세션에 등록
			session.setAttribute("loginMember", dbMember);
			out.println("<script>alert('" + dbMember.getMemName() + "님 반갑습니다!');" +
					"function getContextPath() {\n" + 
					"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
					"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
					"};"
					+ "location.href=getContextPath()+'/main.do'; </script>");
			out.flush();
		}else {
			// sign up
			try {
				loginService.registeMember(member);
			} catch (BizNotEffectedException bne) {
				bne.printStackTrace();
				out.println("<script>alert('이미 등록된 계정입니다..');" +
							"function getContextPath() {\n" + 
							"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
							"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
							"};"
							+ "location.href=getContextPath()+'/main.do'; </script>");
				out.flush();
				
			} catch (Exception e) {
				e.printStackTrace();
				out.println("<script>alert('회원등록에 실패했습니다\\n 전산실에 문의해주세요 010-4403-9382');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/main.do'; </script>");
				out.flush();
			}
			out.println("<script>alert('계정이 등록되었습니다!');" +
					"function getContextPath() {\n" + 
					"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
					"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
					"};"
					+ "location.href=getContextPath()+'/main.do'; </script>");
			out.flush();
		}
		

		
		return "/WEB-INF/views/main/main.jsp";
	}
	
	
	

}
