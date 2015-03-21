package com.mastercard.mcwallet.sampleapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastercard.mcwallet.sampleapp.MasterpassController;
import com.mastercard.mcwallet.sampleapp.MasterpassData;


/**
 * Servlet implementation class P1_PairingServlet
 */
@WebServlet("/P1_PairingServlet")
public class PairingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PairingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
