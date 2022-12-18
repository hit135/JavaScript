package kr.or.youth.main.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.youth.common.vo.PagingVO;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.main.service.IMainService;
import kr.or.youth.main.service.MainServiceImpl;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;
import kr.or.youth.policy.vo.codeVO;
import kr.or.youth.servlet.YouthPolicyProcess;

public class Main implements YouthPolicyProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Main process에 도착");
		
		
		// 서비스 선언
		IMainService mainService = new MainServiceImpl();
		
		// PagingVO pagingVO = new PagingVO();
		SearchVO searchVO = new SearchVO();
		
		// get으로 보낸 페이지 받아오기
		BeanUtils.populate(searchVO, request.getParameterMap());
		// System.out.println("searchVO" + searchVO);
		
		try {
			// 페이징 처리
			// 리스트 불러오기
			List<YouthPolicy> policyList = mainService.getPolicyList(searchVO);
			// System.out.println("policyList : " + policyList);
			request.setAttribute("policyList", policyList);
			request.setAttribute("searchVO", searchVO);
			
			// 선택 박스 불러오기
			// 취업상태
			List<codeVO> empList = mainService.getSelectList("emp_00");
			// System.out.println("empList : " + empList);
			request.setAttribute("empList", empList);
			// 학력상태
			List<codeVO> eduList = mainService.getSelectList("edu_00");
			request.setAttribute("eduList", eduList);
			// 전공상태
			List<codeVO> majList = mainService.getSelectList("maj_00");
			request.setAttribute("majList", majList);
			
		} catch (BizNotEffectedException bne) {
			bne.printStackTrace();
			request.setAttribute("bne", bne);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
		}
		
		return "/WEB-INF/views/main/main.jsp";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
