package mastercard.mcwallet.sampleapp.servlets;

import mastercard.mcwallet.sampleapp.MasterpassData;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet implementation class PreCheckoutDataTypeServlet
 */
@WebServlet("/PreCheckoutDataTypeServlet")
public class PairingConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public PairingConfigurationServlet() {
        super();
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		MasterpassData data = null;
		
		String dataString = request.getParameter("data");
		ObjectMapper mapper = new ObjectMapper();
		if (dataString != null && dataString.length() > 0){
			data = mapper.readValue(dataString.toString(), MasterpassData.class);
		} else {
			data = (MasterpassData) session.getAttribute("data");
		}
		
		String expressString = request.getParameter("express");
		if (expressString != null && expressString.equals("true")){
			data.setExpressCheckoutIndicator(true);
		} else {
			data.setExpressCheckoutIndicator(false);
		}
		
		String dataTypesString = request.getParameter("dataTypes");
		List<String> dataTypes = null;
		if (dataTypesString != null) {
			dataTypes = Arrays.asList(dataTypesString.split("\\s*,\\s*"));
		}
		if (dataTypes != null && dataTypes.size() > 0){
			data.setPairingDataTypes(dataTypes);
		} else {
			data.setPairingDataTypes(null);
		}
		response.setContentType("application/json");
		String mappedJson = mapper.writeValueAsString(data);
		response.getWriter().write(mappedJson);
		
		session.setAttribute("data", data);
	}

}
