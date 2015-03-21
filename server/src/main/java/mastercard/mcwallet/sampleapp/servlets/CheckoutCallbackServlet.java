package mastercard.mcwallet.sampleapp.servlets;

import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class CheckoutCallbackServlet
 */
@WebServlet({ "/CheckoutCallbackServlet", "/checkoutCallback" })
public class CheckoutCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public CheckoutCallbackServlet() {
        super();
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		
		try {
			MasterpassController controller = new MasterpassController(data);
			data = controller.getRequestToken(data);
			data.setShoppingCart(controller.parseShoppingCartFile(data.getRequestToken(), data.getCallbackDomain()));
			controller.postShoppingCart(data);
			
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			String mappedJson = mapper.writeValueAsString(data);
			response.getWriter().write(mappedJson);
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		session.setAttribute("data", data);
		
	}

}
