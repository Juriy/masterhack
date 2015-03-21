package com.mastercard.mcwallet.sampleapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastercard.mcwallet.sampleapp.MasterpassController;
import com.mastercard.mcwallet.sampleapp.MasterpassData;

/**
 * Servlet implementation class O3_PairingCallbackServlet
 */
@WebServlet("/O3_PairingCallbackServlet")
public class PairingCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PairingCallbackServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	MasterpassData data = (MasterpassData) session.getAttribute("data");
    	data.setErrorMessage(null);
		
    	data.setPairingToken(request.getParameter("pairing_token"));
    	data.setPairingVerifier(request.getParameter("pairing_verifier"));
    	
		Boolean express = Boolean.valueOf(request.getParameter("express"));
		
		try {
			MasterpassController controller = new MasterpassController(data);
			data = controller.getLongAccessToken(data);
			
			Cookie cookie = new Cookie("longAccessToken", data.getLongAccessToken());
			cookie.setMaxAge(60*60*24*7); //7 day length for the cookie.
			response.addCookie(cookie);

		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		
		session.setAttribute("data", data);
		request.getRequestDispatcher("PairingCallback.jsp").forward(request, response);
	}



}
