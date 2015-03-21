package com.mastercard.mcwallet.sampleapp.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.mastercard.mcwallet.sampleapp.MasterpassController;
import com.mastercard.mcwallet.sampleapp.MasterpassData;
import com.mastercard.mcwallet.sdk.MasterPassServiceRuntimeException;

/**
 * Servlet implementation class PreCheckoutDataTypeServlet
 */
@WebServlet("/PreCheckoutDataTypeServlet")
public class PairingConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PairingConfigurationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
