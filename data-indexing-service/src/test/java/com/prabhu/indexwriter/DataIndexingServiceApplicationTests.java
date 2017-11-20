package com.prabhu.indexwriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataIndexingServiceApplicationTests {

	@Autowired
	Configuration config;
	
	@Autowired
	DataIndexWriter indexWriter;

	@Test
	public void Test_Configuration() {
		Assert.assertEquals(config.getFilepath(), "/Users/prabhu/Desktop/prabhu/learning/java-bytes/data/index.txt");
		Assert.assertEquals(config.getIndexFolderPath(), "file:/Users/prabhu/Desktop/prabhu/learning/java-bytes/data/index");
	}
	
	@Test
	public void Test_FileIteration(){
		indexWriter.writeIndex();
	}

}
