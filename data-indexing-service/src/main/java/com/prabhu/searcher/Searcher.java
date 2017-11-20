package com.prabhu.searcher;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.prabhu.indexwriter.Configuration;

@Component
public class Searcher {

	@Autowired
	Configuration config;
	
	SearcherManager searchManager;
	
	
	public void init() throws IOException, URISyntaxException{
		Directory directory = FSDirectory.open(Paths.get(new URI(config.getIndexFolderPath())));
		searchManager = new SearcherManager(directory, null);

	}

	private IndexSearcher getSearchManager() throws IOException{
		return searchManager.acquire();
	}
	public void getSearchItems(SearchCriteria searchCriteria) throws IOException, URISyntaxException{
		init();
		Term term = new Term(searchCriteria.getFieldName(),searchCriteria.getFieldValue());
		TopDocs docs =  getSearchManager().search(new WildcardQuery(term), 100);
		Arrays.asList(docs.scoreDocs).stream().forEach(s->{try {
			Document document = getSearchManager().doc(s.doc);
			System.out.println(String.format("Document ID :{%s} , Name:{%s}, Location:{%s}, Time:{%s}" ,document.get("id"),document.get("name"),document.get("locationInfo"),document.get("locationTime")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
	}
}
