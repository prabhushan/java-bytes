package com.prabhu.activemq;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class QueueBrowser {

	@Autowired
	private ConnectionFactory connectionFactory;

	private Connection connection;

	Logger log;

	@PostConstruct
	void startConnection() throws JMSException {
		connection = connectionFactory.createConnection();
		connection.start();
		log = Logger.getLogger("QueueBrowser");
	}

	public void browser() throws JMSException {
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the Browse Destination and the Reply To location
		Destination requestBrowse = session.createTopic(ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION);
		Destination browseDest = session.createQueue("test-browser-queue");

		// Create the "Browser"
		MessageConsumer browser = session.createConsumer(browseDest);

		// Send the browse request
		MessageProducer producer = session.createProducer(requestBrowse);
		Message request = session.createMessage();
		request.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION, ScheduledMessage.AMQ_SCHEDULER_ACTION_BROWSE);
		request.setJMSReplyTo(browseDest);
		producer.send(request);

		// Message scheduled = browser.receive();
		// while (scheduled != null) {
		// log.info(scheduled.);
		// }

	}

}
