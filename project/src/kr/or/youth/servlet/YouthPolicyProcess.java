package kr.or.youth.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface YouthPolicyProcess {
	
	String process(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
