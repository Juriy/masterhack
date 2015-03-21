package mastercard.mcwallet.sampleapp.servlets;


import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet implementation class P1_PairingServlet
 */
@WebServlet("/P1_PairingServlet")
public class PairingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public PairingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		 
        if(data == null){
            data = new MasterpassData();            
        }
		data.setErrorMessage(null);
		data.setAppBaseUrl(request.getScheme() + "://" + request.getServerName());
		data.setContextPath(request.getContextPath());
		try {
			MasterpassController controller = new MasterpassController(data);
			if (request.getParameter("checkout") == null){
				data = controller.processParameters(request, data);
			} else {
				data.setCallbackPath(data.getConnectedCallbackPath());
			}
			data = controller.getPairingToken(data);
			
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		session.setAttribute("data", data);
		request.getRequestDispatcher("Pairing.jsp").forward(request, response);
	}

}
