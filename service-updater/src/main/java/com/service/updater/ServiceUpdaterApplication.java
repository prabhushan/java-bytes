package com.service.updater;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import com.service.excel.IExcelService;
import com.service.model.InputFields;

@SpringBootApplication
@ComponentScan(basePackages = "com.service.*")
public class ServiceUpdaterApplication implements CommandLineRunner {

	@Autowired
	Environment env;

	@Autowired
	IExcelService excelService;

	public static void main(String[] args) {
		SpringApplication.run(ServiceUpdaterApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		File file = new File(env.getProperty("file.path"));
		List<InputFields> ListInputFields = excelService.readFieldsFromFile(file);
		ListInputFields.stream().forEach(s -> System.out.println(s.toString()));
		// call your service
		excelService.writeFieldintoFile(file, ListInputFields);
	}

}
