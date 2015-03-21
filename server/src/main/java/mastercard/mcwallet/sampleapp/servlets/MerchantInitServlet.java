package mastercard.mcwallet.sampleapp.servlets;



import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;
import mastercard.mcwallet.sdk.MasterPassServiceRuntimeException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class O2_MerchantInitServlet
 */
@WebServlet("/O2_MerchantInitServlet")
public class MerchantInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public MerchantInitServlet() {
        super();
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // If there is an error on the wallet, MasterPass will redirect back to the calling page
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		data.setErrorMessage(null);
		 
		try {
			MasterpassController controller = new MasterpassController(data);
			
			if ((request.getParameter("checkout") != null) && (request.getParameter("checkout").equals("true"))){
				try {
					data = controller.getRequestToken(data);
					data = controller.getPairingToken(data);
					controller.postMerchantInit(data);
					data = controller.postShoppingCart(data);
					data.setCallbackPath("/OauthCallbackServlet");
				} catch (Exception e) {
					data.setErrorMessage(e.getMessage());
					throw new MasterPassServiceRuntimeException(e);
				}
			} else {
				controller.postMerchantInit(data);
			}
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		session.setAttribute("data", data);
		request.getRequestDispatcher("MerchantInitialization.jsp").forward(request, response);
	}

}
