package com.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import simple.bank.TellerLocal;

/**
 * Message-Driven Bean implementation class for: SimpleMDB
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/TestQueue") })
public class SimpleMDB implements MessageListener {

	@EJB(beanName = "Teller")
	TellerLocal teller;

	/**
	 * Default constructor.
	 */
	public SimpleMDB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			System.out
					.println("Received a message. Waking up and getting all funds from the bank");
			TextMessage mesg = (TextMessage) message;
			System.out.println("The message was from " + mesg.getText());
			long result = teller.getTotalFunds();
			System.out.println("Got all funds ... the bank has " + result);
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
