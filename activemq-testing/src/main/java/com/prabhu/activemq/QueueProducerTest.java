package com.prabhu.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tista.aps.queue.exception.ApsQueueException;
import com.tista.aps.queue.producer.QueueProducer;

@Service
public class QueueProducerTest {

	@Value("${queue.url}")
	private String queueBrokerUrl;

	@Autowired
	QueueProducer queueProd;

	public void produceMessages(String text) throws ApsQueueException {

		queueProd.message("test", text);
	}
}
