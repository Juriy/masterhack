package com.mastercard.mcwallet.sampleapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastercard.mcwallet.sampleapp.MasterpassController;
import com.mastercard.mcwallet.sampleapp.MasterpassData;


/**
 * Servlet implementation class CartInitialServlet
 */
public class CartInitialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartInitialServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // If there is an error on the wallet, MasterPass will redirect back to the calling page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		data.setErrorMessage(null);
		data.setAppBaseUrl(request.getScheme() + "://" + request.getServerName());
		String callbakPath = data.getCallbackPath().replace("OauthCallbackServlet", "CartCallbackServlet");
		data.setCallbackUrl(data.getCallbackDomain() + callbakPath);
		try {
			MasterpassController controller = new MasterpassController(data);
			data = controller.processParameters(request, data);
			data = controller.getRequestToken(data);
			data.setShoppingCart(controller.parseShoppingCartFile(data.getRequestToken(), data.getCallbackDomain()));
			controller.postShoppingCart(data);
			
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		session.setAttribute("data", data);
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
	}

}
