package kr.or.youth.policy.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.youth.policy.service.IPolicyService;
import kr.or.youth.policy.service.PolicyServiceImpl;
import kr.or.youth.servlet.YouthPolicyProcess;

public class InputPolicy implements YouthPolicyProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String policyData = request.getParameter("arrayPolicy");
		
		System.out.println("policyData : " + policyData);
		
		IPolicyService policyService = new PolicyServiceImpl();
		
		try {
			policyService.insertPolicy(policyData);
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return "/WEB-INF/views/policy/inputPolicy.jsp";
	}

}
