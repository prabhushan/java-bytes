package com.prabhu.activemq;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "com.tista.aps.*", "com.prabhu.*" })
@ComponentScan(basePackages = { "com.tista.aps.*", "com.prabhu.*" })
public class ActivemqTestingApplication implements CommandLineRunner {

	@Autowired
	QueueProducerTest queueProducer;

	public static void main(String[] args) {
		System.out.println(
				"13) Strip whitespace >>>" + StringUtils.replace("     a  00      ", "0", "").trim().isEmpty());

		// SpringApplication.run(ActivemqTestingApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		File file = FileUtils.toFile(this.getClass().getClassLoader().getResource("1MB"));
		String text = FileUtils.readFileToString(file, Charset.defaultCharset());
		for (int i = 0; i < 10000; i++) {

			queueProducer.produceMessages(text);
			System.out.println(i + 1);
		}
	}
}
