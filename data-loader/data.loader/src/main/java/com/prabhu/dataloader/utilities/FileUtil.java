package com.prabhu.dataloader.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;


public class FileUtil {

	public static void writeToFile(File file,String lines) throws IOException{
	FileUtils.writeStringToFile(file, lines,true);
	FileUtils.writeStringToFile(file, "\n",true);
	}
}
