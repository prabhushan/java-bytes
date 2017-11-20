package com.prabhu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prabhu.indexwriter.DataIndexWriter;
import com.prabhu.searcher.SearchCriteria;
import com.prabhu.searcher.Searcher;

@SpringBootApplication
public class DataIndexingServiceApplication implements CommandLineRunner{

	@Autowired
	DataIndexWriter indexWriter;
	
	@Autowired
	Searcher searcher;
	
	public static void main(String[] args) {
		SpringApplication.run(DataIndexingServiceApplication.class, args);
		
	}

	
	@Override
	public void run(String... args) throws Exception {
		indexWriter.writeIndex();
		//SearchCriteria criteria = new SearchCriteria("id","5720860cdf8dab1610c75009","IN");
		SearchCriteria criteria = new SearchCriteria("name","bara","IN");
		searcher.getSearchItems(criteria);
	}
}
