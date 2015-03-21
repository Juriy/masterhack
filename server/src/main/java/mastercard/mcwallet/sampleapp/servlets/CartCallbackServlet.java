package mastercard.mcwallet.sampleapp.servlets;



import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet implementation class CartCallbackServlet
 */
public class CartCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public CartCallbackServlet() {
        super();
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		data.setErrorMessage(null);

		if(request.getParameterMap().containsKey("oauth_token") && request.getParameterMap().containsKey("oauth_verifier") && request.getParameterMap().containsKey("checkout_resource_url") ) {
			data.setRequestToken(request.getParameter("oauth_token"));
			data.setVerifier(request.getParameter("oauth_verifier"));
			data.setCheckoutResourceURL(request.getParameter("checkout_resource_url"));

			try {
				MasterpassController controller = new MasterpassController(data);

				data = controller.getAccessToken(data);
				data = controller.getCheckoutData(data);
				data.setShoppingCart(controller.parseShoppingCartFile(data.getRequestToken(), data.getCallbackDomain()));
			}catch (Exception e) {
				data.setErrorMessage(e.getMessage());
			}

			session.setAttribute("data", data);
			request.getRequestDispatcher("ReviewOrder.jsp").forward(request, response);
		}
		// if the parameters are not returned then send the user back to the initial cart page.
		else {
			try {
				MasterpassController controller = new MasterpassController(data);
				data.setShoppingCart(controller.parseShoppingCartFile(data.getRequestToken(), data.getCallbackDomain()));
			}catch (Exception e) {
				data.setErrorMessage(e.getMessage());
			}
			session.setAttribute("data", data);
			request.getRequestDispatcher("Cart.jsp").forward(request, response);
		}
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

}
