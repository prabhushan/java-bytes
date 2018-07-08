package com.service.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.service.model.InputFields;

public interface IExcelService {
	
	public List<InputFields> readFieldsFromFile(File input) throws IOException;
	
	public boolean writeFieldintoFile(File output,List<InputFields> listFields) throws IOException;

}
