package com.tista.aps.entity.verifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class ReportGenerator {

	public static void generateReport(File reportFile, String lines) throws IOException {

		ArrayList<String> arrListString = new ArrayList<>();
		arrListString.add(lines);
		FileUtils.writeLines(reportFile, arrListString, true);
	}

	public static void addHeader(File reportFile) throws IOException {
		ArrayList<String> arrListString = new ArrayList<>();
		arrListString.add("Entity Profile ID, Custom Matched Identities, ER matched Identities");
		FileUtils.writeLines(reportFile, arrListString, true);

	}

	private ReportGenerator() {

	}
}
