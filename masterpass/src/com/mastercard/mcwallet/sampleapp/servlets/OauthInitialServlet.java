package com.mastercard.mcwallet.sampleapp.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastercard.mcwallet.sampleapp.MasterpassController;
import com.mastercard.mcwallet.sampleapp.MasterpassData;



/**
 * Servlet implementation class Step1Servlet
 */
public class OauthInitialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OauthInitialServlet() {
        super();
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		
        if(data == null){
            data = new MasterpassData();            
        }
		data.setErrorMessage(null);
		data.setAppBaseUrl(request.getScheme() + "://" + request.getServerName());
		data.setContextPath(request.getContextPath());
		try {
			MasterpassController controller = new MasterpassController(data);
			String str = request.getRequestURL()+"?";
			Enumeration<String >paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()){
				String paramName = paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				for (int i = 0; i<paramValues.length; i++){
					String paramValue = paramValues[i];
					str=str+paramName+"="+paramValue;
				}
				str=str+"&";
			}
			data = controller.processParameters(request, data);
			data = controller.getRequestToken(data);
			
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		
		session.setAttribute("data", data);
		request.getRequestDispatcher("GetRequestToken.jsp").forward(request, response);
	}

}
