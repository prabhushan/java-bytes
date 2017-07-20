package com.prabhu.indexing.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;

public class IndexingService {

	public static final String index_path = "file:/Users/prabhu/Desktop/prabhu/learning/java-bytes/java-bytes/index";
	public static void main(String[] args) throws IOException, URISyntaxException {
		//new IndexingService().writeIndex();
	    new IndexingService().readIndex();
	}
	
	private IndexWriter getIndexWriter() throws IOException, URISyntaxException {
		Directory directory = FSDirectory.open(Paths.get(new URI(index_path)));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
		indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);  
		return new IndexWriter(directory, indexWriterConfig);
	}
	
	public void readIndex() throws IOException, URISyntaxException{
		Directory directory = FSDirectory.open(Paths.get(new URI(index_path)));
		DirectoryReader.open(directory);

		SearcherManager searchManager = new SearcherManager(directory, null);
		IndexSearcher searcher= searchManager.acquire();
		try{
			for (int i = 0; i < 100; i++) {
				Query query1 = new WildcardQuery(new Term("name","sai"+i+"*"));
				queryIndex(searcher,query1);	
			}
			
			
		}
		
		finally{
			searchManager.close();
			searchManager.release(searcher);
		}
	}

	private void queryIndex(IndexSearcher searcher, Query query) throws IOException {
		System.out.println("Next ####");
		TopDocs docs =searcher.search(query, 10);
		ScoreDoc[] scoreDoc = docs.scoreDocs;
		for (int i = 0; i < scoreDoc.length; i++) {
			System.out.println(searcher.doc(scoreDoc[i].doc));
		}
	}
	
	public void writeIndex() throws IOException{
		IndexWriter indexWriter = null;

		try{
			indexWriter = getIndexWriter();
			Document document = new Document();
			
			for(int i=0;i<10000000;i++){
				document = new Document();
				document.add(new TextField("name","sai"+i+System.currentTimeMillis(),Store.YES));

				indexWriter.addDocument(document);
				if(i%10000 == 0){
					indexWriter.commit();
				}
			}

			 

		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(indexWriter!=null)
			indexWriter.commit();
			else{
				System.out.println("IndexWriter cannot be null");
			}
		}
		

	}

}
