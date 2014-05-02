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
import com.entity.SavingsAccount;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {

	@EJB(beanName = "Teller")
	TellerLocal teller;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("todo");
		if ("ID Search".equals(action)) {
			String idString = request.getParameter("id");
			int id = new Integer(idString).intValue();
			BankAccount ba = teller.findAccount(id);
			request.setAttribute("account", ba);
		}
		if ("Balance Search".equals(action)) {
			String amountString = request.getParameter("amount");
			int amount = new Integer(amountString).intValue();
			List<BankAccount> results = teller.findWithBalance(amount);
			request.setAttribute("results", results);
		}
		if ("List All Accounts".equals(action)) {
			List<BankAccount> results = teller.listAllAccounts();
			request.setAttribute("results", results);
		}
		if ("List All Savings Accounts".equals(action)) {
			List<SavingsAccount> results = teller.listAllSavingsAccounts();
			request.setAttribute("results", results);
		}
		request.getRequestDispatcher("displayresults.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
