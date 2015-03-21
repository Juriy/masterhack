package com.mastercard.mcwallet.sampleapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastercard.mcwallet.sampleapp.MasterpassController;
import com.mastercard.mcwallet.sampleapp.MasterpassData;
import com.mastercard.mcwallet.sdk.MasterPassServiceRuntimeException;


/**
 * Servlet implementation class TestMerchantCallbackServlet
 */
public class OauthCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OauthCallbackServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
