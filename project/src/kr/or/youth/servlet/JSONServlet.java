package kr.or.youth.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.main.service.IMainService;
import kr.or.youth.main.service.MainServiceImpl;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;

import java.io.PrintWriter;
import java.util.List;

public class JSONServlet extends HttpServlet{
	
	  private static final long serialVersionUID = 1L;
      
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public JSONServlet() {
	        super();
	    }
	 
	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("PolicyData process");
			response.setContentType("application/x-json; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			// object > json
			ObjectMapper mapper = new ObjectMapper();
			IMainService mainService = new MainServiceImpl();
			//PagingVO pagingVO = new PagingVO();
			SearchVO searchVO = new SearchVO();
			String jsonPolicyList = null;
			String jsonPagingVO = null;
			// get으로 던진 파라미터 받아오기
			try {
				BeanUtils.populate(searchVO, request.getParameterMap());
				// pagingVO 셋팅
				searchVO.pageSetting();
				System.out.println("pagingVO" + searchVO);
				List<YouthPolicy> policyList = mainService.getPolicyList(searchVO);
				jsonPolicyList = mapper.writeValueAsString(policyList);
				jsonPagingVO = mapper.writeValueAsString(searchVO);
				System.out.println("jsonPolicyList이거 모양이..?" + jsonPolicyList);
				System.out.println("jsonPagingVO의 모습" + jsonPagingVO);
				// request에 담기
				request.setAttribute("jsonPolicyList", jsonPolicyList);
			} catch (BizNotEffectedException bne) {
				bne.printStackTrace();
				request.setAttribute("bne", bne);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("e", e);
			}
			
			// 배열로 받음
			out.print("[" + jsonPolicyList +","+ jsonPagingVO+ "]"); //response   
	    }
	 
}
