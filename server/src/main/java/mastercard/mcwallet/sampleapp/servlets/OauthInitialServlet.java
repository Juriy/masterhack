package mastercard.mcwallet.sampleapp.servlets;


import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


/**
 * Servlet implementation class Step1Servlet
 */
public class OauthInitialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public OauthInitialServlet() {
        super();
    }


	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
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
