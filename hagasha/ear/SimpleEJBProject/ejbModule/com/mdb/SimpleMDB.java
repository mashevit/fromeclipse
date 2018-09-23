package com.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import simple.bank.TellerLocal;

/**
 * Message-Driven Bean implementation class for: SimpleMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="jms/queue/example")})
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
		// TODO Auto-generated method stub
		try {
			
//			InitialContext iniCtx = new InitialContext();
//			teller = (TellerLocal) iniCtx
//					.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");
			System.out.println("Received a message. Waking up and getting all funds from the bank");
			TextMessage mesg = (TextMessage) message;

			System.out.println("The message was from " + mesg.getText());

			long result = teller.getTotalFunds();
			System.out.println("got total funds... the bank has " + result);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

}
