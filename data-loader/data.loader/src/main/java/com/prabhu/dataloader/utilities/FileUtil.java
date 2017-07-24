package com.prabhu.dataloader.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;


public class FileUtil {

	public static void writeToFile(String lines,String filePath) throws IOException{
	FileUtils.writeStringToFile(new File(filePath), lines,true);
	}
}
