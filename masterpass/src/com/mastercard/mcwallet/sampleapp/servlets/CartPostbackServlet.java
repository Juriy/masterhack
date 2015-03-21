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
 * Servlet implementation class CartPostbackServlet
 */
public class CartPostbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartPostbackServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = (MasterpassData) session.getAttribute("data");
		data.setErrorMessage(null);
		
		try {
			MasterpassController controller = new MasterpassController(data);
			data = controller.logTransaction(data);
		}catch (Exception e) {
			data.setErrorMessage(e.getMessage());
		}
		
		session.setAttribute("data", data);
		request.getRequestDispatcher("OrderComplete.jsp").forward(request, response);
	}

}
