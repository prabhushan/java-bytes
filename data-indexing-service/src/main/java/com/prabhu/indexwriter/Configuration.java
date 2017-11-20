package com.prabhu.indexwriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@ConfigurationProperties()
@Component
public class Configuration {
	
	@Value("${data.filepath}")
	private String filepath;
	
	@Value("${data.index-folderpath}")
	private String indexFolderPath;

	public String getIndexFolderPath() {
		return indexFolderPath;
	}

	public void setIndexFolderPath(String indexFolderPath) {
		this.indexFolderPath = indexFolderPath;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
