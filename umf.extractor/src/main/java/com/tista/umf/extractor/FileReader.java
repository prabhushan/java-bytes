package com.tista.umf.extractor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class FileReader {

	public static void readFiles(String path, String targetPath) throws IOException {
		File folder = new File(path);
		File targetFile = new File(targetPath);
		File[] listOfFiles = folder.listFiles();
		List<String> data = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName() + i);
				String fileContents = FileUtils.readFileToString(listOfFiles[i], Charset.defaultCharset());
				String sourceCode = (StringUtils.substringBetween(fileContents, "<DSRC_REF>", "</DSRC_REF>"));
				String sourceID = (StringUtils.substringBetween(fileContents, "<DSRC_CODE>", "</DSRC_CODE>"));

				data.add(new StringBuilder(sourceCode).append(",").append(sourceID).toString());
				FileUtils.writeLines(targetFile, data, true);
				data.clear();
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

	}

}
