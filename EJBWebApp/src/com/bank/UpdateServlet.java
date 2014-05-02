package com.bank;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simple.bank.TellerLocal;

import com.entity.BankAccount;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {

	@EJB(beanName = "Teller")
	TellerLocal teller;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
		String idString = request.getParameter("id");
		int id = new Integer(idString).intValue();
		String amountString = request.getParameter("amount");
		int amount = new Integer(amountString).intValue();
		BankAccount ba;
		if ("Deposit".equals(action)) {
			ba = teller.deposit(id, amount);
			request.setAttribute("account", ba);
		}
		if ("Withdraw".equals(action)) {
			ba = teller.withdraw(id, amount);
			request.setAttribute("account", ba);
		}
		if ("Close Account".equals(action)) {
			teller.closeAccount(id);
			request.setAttribute("closed", "" + id);
		}
		request.getRequestDispatcher("displayaccount.jsp").forward(request,
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
