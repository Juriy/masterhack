package mastercard.mcwallet.sampleapp.servlets;



import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Servlet implementation class O5_PreCheckout
 */
@WebServlet("/O5_PreCheckout")
public class PreCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public PreCheckoutServlet() {
        super();
    }


	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		data.setErrorMessage(null);
		
		try{
			Cookie[] cookies;
			cookies = request.getCookies();
			if (cookies != null){
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("longAccessToken")){
						data.setLongAccessToken(cookie.getValue());
					}
				}
			}
			
			MasterpassController controller = new MasterpassController(data);
			data = controller.getPreCheckoutData(data);
			
			Cookie tokenCookie = new Cookie("longAccessToken", data.getLongAccessToken());
			tokenCookie.setMaxAge(60*60*24*7); //7 day length for the cookie.
			response.addCookie(tokenCookie);
			String express = request.getParameter("express");
			
				//get a new token
				data = controller.getRequestToken(data);
				//shopping cart
				data = controller.postShoppingCart(data);
			
			
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		session.setAttribute("data", data);
		request.getRequestDispatcher("PreCheckout.jsp").forward(request, response);
	}

}
