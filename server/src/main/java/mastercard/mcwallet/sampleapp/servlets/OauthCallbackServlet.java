package mastercard.mcwallet.sampleapp.servlets;



import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;
import mastercard.mcwallet.sdk.MasterPassServiceRuntimeException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Servlet implementation class TestMerchantCallbackServlet
 */
public class OauthCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public OauthCallbackServlet() {
        super();
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	MasterpassData data = (MasterpassData) session.getAttribute("data");
		data.setErrorMessage(null);
		
		data.setRequestToken(request.getParameter("oauth_token"));
		data.setVerifier(request.getParameter("oauth_verifier"));
		data.setCheckoutResourceURL(request.getParameter("checkout_resource_url"));

		if ((request.getParameter("pairing_token") != null) && (request.getParameter("pairing_verifier") != null)){
			data.setPairingToken(request.getParameter("pairing_token"));
			data.setPairingVerifier(request.getParameter("pairing_verifier"));
			MasterpassController controller = new MasterpassController(data);
			try {
				data = controller.getLongAccessToken(data);
				Cookie cookie = new Cookie("longAccessToken", data.getLongAccessToken());
				cookie.setMaxAge(60*60*24*7); //7 day length for the cookie.
				response.addCookie(cookie);
			} catch (Exception e) {
				e.printStackTrace();
				throw new MasterPassServiceRuntimeException(e);
			}
		}
		session.setAttribute("data", data);
		request.getRequestDispatcher("Callback.jsp").forward(request, response);
	}
	
}
