package kr.or.youth.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class YouthPolicyServlet extends HttpServlet {

	private Map<String, YouthPolicyProcess> npMap = new HashMap<String, YouthPolicyProcess>();
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//super.init();
		
		//String uriLocation = "classpath:/config/uri_mapping.properties";
		String uriLocation = "/WEB-INF/classes/kr/or/youth/config/uri_mapping.properties";
		String fullPath = getServletContext().getRealPath(uriLocation);
		// System.out.println("fullPath: "+ fullPath);

		FileReader fr = null;
		Properties prop = null;
		try {
			fr = new FileReader(fullPath);
			prop  = new Properties();
			prop.load(fr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("prop.keySet(): "+ prop.keySet());
		System.out.println("prop.values(): "+ prop.values());
		
		
		Iterator<Object> keyIterator = prop.keySet().iterator();
		while (keyIterator.hasNext()) {
			String uri = (String) keyIterator.next();
			//System.out.println("uri : "+ uri );
			
			String targetClassLocation = prop.getProperty(uri);
			// System.out.println("targetClassLocation : "+ targetClassLocation);
		
			try {
				Class<?> targetClass = Class.forName(targetClassLocation);
				
				YouthPolicyProcess  np =  (YouthPolicyProcess) targetClass.newInstance();
				npMap.put(uri, np);
				System.out.println("uri : "+ uri + ", np : "+ np.getClass().getName() +"을 Map에 담았습니다.");
				 
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		
	}
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		
		System.out.println("NextITServlet_service");
		
		//http://localhost:8080/nextit01/nextitLogin.do
		
		/*String uri = req.getRequestURI();
		System.out.println("uri1 : "+ uri);
		uri =  uri.substring(req.getContextPath().length());
		System.out.println("uri2 : "+ uri);
		if(uri.equals("/nextitLogin.do")) {
			System.out.println("nextitLogin.do");
			
			Login login = new Login();
			String viewPage =login.loginPage();
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
		}*/
		
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		YouthPolicyProcess np =  npMap.get(uri);
		if(np == null) {
			resp.sendError(404);
		}else {
			String viewPage;
			try {
				viewPage = np.process(req, resp);
				System.out.println("viewPage : "+ viewPage);
				
				if(viewPage.startsWith("redirect:")) {
					resp.sendRedirect(req.getContextPath()
							+ viewPage.substring("redirect:".length()));
				}else {
					RequestDispatcher rd = req.getRequestDispatcher(viewPage);
					rd.forward(req, resp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
			
			
			
			
	}
}
