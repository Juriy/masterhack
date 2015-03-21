package com.mastercard.mcwallet.sampleapp.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastercard.mcwallet.sampleapp.MasterpassController;
import com.mastercard.mcwallet.sampleapp.MasterpassData;


/**
 * Servlet implementation class OauthCheckoutServlet
 */
public class ExpressCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpressCheckoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
