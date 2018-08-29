package com.tista.aps.entity.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

public class ReportGenerator {

	public static void generateReport(File reportFile, String lines) throws IOException {
		if (reportFile == null || !reportFile.exists() || !reportFile.canWrite()) {
			throw new IOException("Report File is not available or unable to write");
		}
		FileUtils.writeStringToFile(reportFile, lines, Charset.defaultCharset(), true);
	}

	private ReportGenerator() {

	}
}
