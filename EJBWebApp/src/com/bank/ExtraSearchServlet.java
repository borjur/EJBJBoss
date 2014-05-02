package com.bank;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simple.bank.TellerLocal;

import com.entity.BankAccount;
import com.entity.PhoneNumber;

/**
 * Servlet Class
 * 
 */
public class ExtraSearchServlet extends HttpServlet {

	public ExtraSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EJB(beanName = "Teller")
	TellerLocal teller;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("todo");
		if ("Phone Number Search".equals(action)) {
			String idString = req.getParameter("id");
			int id = new Integer(idString).intValue();
			List<PhoneNumber> numbers = teller.findNumbersForOwner(id);
			req.setAttribute("numbers", numbers);
		}
		if ("Account Search".equals(action)) {
			String areacodeString = req.getParameter("areacode");
			int areacode = new Integer(areacodeString).intValue();
			List<BankAccount> accounts = teller
					.findAccountsForAreaCode(areacode);
			req.setAttribute("accounts", accounts);
		}
		if ("Telemarketers Click Here".equals(action)) {
			String amountString = req.getParameter("amount");
			int amount = new Integer(amountString).intValue();
			List<PhoneNumber> phoneNumbers = teller
					.findNumbersForAmount(amount);
			req.setAttribute("phoneNumbers", phoneNumbers);
		}
		req.getRequestDispatcher("extradisplayresults.jsp").forward(req, resp);
	}
}
