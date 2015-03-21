package mastercard.mcwallet.sampleapp.servlets;



import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Servlet implementation class OauthCheckoutServlet
 */
public class ExpressCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public ExpressCheckoutServlet() {
        super();
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		data.setErrorMessage(null);
		
		try{
			MasterpassController controller = new MasterpassController(data);
			data.setPrecheckoutCardId(request.getParameter("cardSubmit"));
			data.setPrecheckoutShippingId(request.getParameter("addressSubmit"));
			
			
			Cookie tokenCookie = new Cookie("longAccessToken", data.getLongAccessToken());
			tokenCookie.setMaxAge(60*60*24*7); //7 day length for the cookie.
			response.addCookie(tokenCookie);
			
			data = controller.getExpressCheckoutData(data);
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		session.setAttribute("data", data);
		request.getRequestDispatcher("ExpressCheckout.jsp").forward(request, response);
	}

}
