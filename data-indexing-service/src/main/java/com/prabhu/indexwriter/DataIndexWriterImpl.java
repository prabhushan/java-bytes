package com.prabhu.indexwriter;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prabhu.indexwriter.model.MedicalIndex;
import com.prabhu.searcher.LowerCaseAnalyzer;

@Component
public class DataIndexWriterImpl implements DataIndexWriter {

	private Gson gson = new GsonBuilder().create();
	@Autowired
	Configuration config;

	@Override
	public void writeIndex() {
		try {
			createIndex();
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private IndexWriter getIndexWriter() throws IOException, URISyntaxException {
		Directory directory = FSDirectory.open(Paths.get(new URI(config.getIndexFolderPath())));
		Version matchVersion = Version.LUCENE_5_3_0;
		Analyzer analyzr = new LowerCaseAnalyzer(matchVersion);
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzr);
		indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		
		return new IndexWriter(directory, indexWriterConfig);
	}

	private void createIndex() throws IOException, URISyntaxException {
		IndexWriter writer = getIndexWriter();
		try {
			LineIterator iterator = FileUtils.lineIterator(new File(config.getFilepath()));
			int commitCount = 0;
			while (iterator.hasNext()) {
				writer.addDocument(getDocument(iterator.next()));
				commitCount++;
				if (commitCount % 10000 == 0) {
					writer.commit();
					System.out.println("Total Commited ==> " + commitCount);
				}
			}
		} finally {
			writer.commit();
			writer.close();

		}

	}

	private Document getDocument(String data) {
		MedicalIndex medicalIndex = gson.fromJson(data, MedicalIndex.class);
		Document document = new Document();
		document.add(new StringField("id", medicalIndex.get_id().get$oid(), Store.YES));
		document.add(new TextField("name", medicalIndex.getDoctorDetails().getName(), Store.YES));
		document.add(
				new StringField("locationInfo", medicalIndex.getListPracticeDetails()[0].getPracticeInfo(), Store.YES));
		document.add(new StringField("locationTime", medicalIndex.getListPracticeDetails()[0].getTimings(), Store.YES));
		return document;

	}
}
