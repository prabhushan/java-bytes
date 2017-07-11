package com.prabhu.indexing.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexingService {

	public static final String index_path = "file:/Users/prabhu/Desktop/prabhu/learning/java-bytes/java-bytes/index";
	public static void main(String[] args) throws IOException, URISyntaxException {
		new IndexingService().writeIndex();
		new IndexingService().readIndex();
	}
	
	private IndexWriter getIndexWriter() throws IOException, URISyntaxException {
		Directory directory = FSDirectory.open(Paths.get(new URI(index_path)));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
		indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);  
		return new IndexWriter(directory, indexWriterConfig);
	}
	
	public void readIndex() throws IOException, URISyntaxException{
		Directory directory = FSDirectory.open(Paths.get(new URI(index_path)));

		SearcherManager searchManager = new SearcherManager(directory, null);
		IndexSearcher searcher= searchManager.acquire();

		try{
			Query query = new TermQuery(new Term("name","sai"));
			//List<String>
			TopDocs docs =searcher.search(query, 10);
			ScoreDoc[] scoreDoc = docs.scoreDocs;
			for (int i = 0; i < scoreDoc.length; i++) {
				System.out.println(searcher.doc(scoreDoc[i].doc));
			}	
		}
		
		finally{
			//searchManager.close();
			searchManager.release(searcher);
		}
	}
	
	public void writeIndex() throws IOException{
		IndexWriter indexWriter = null;

		try{
			indexWriter = getIndexWriter();
			Document document = new Document();
			document.add(new TextField("name","sai"+System.currentTimeMillis(),Store.YES));
			indexWriter.addDocument(document);

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
