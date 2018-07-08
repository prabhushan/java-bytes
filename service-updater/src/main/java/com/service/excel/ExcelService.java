package com.service.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.service.model.InputFields;

@Component
public class ExcelService implements IExcelService {

	@Override
	public List<InputFields> readFieldsFromFile(File input) throws IOException {
		FileInputStream file = new FileInputStream(input);
		try (Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			List<InputFields> listInputFields = new ArrayList<>();

			for (Row row : sheet) {

				Cell cell = row.getCell(0);
				cell.setCellType(CellType.STRING);

				Cell cell1 = row.getCell(1);
				cell1.setCellType(CellType.STRING);

				Cell cell2 = row.getCell(2);
				cell2.setCellType(CellType.STRING);

				Cell cell3 = row.getCell(3);
				cell3.setCellType(CellType.STRING);

				Cell cell4 = row.getCell(4);
				cell4.setCellType(CellType.STRING);

				InputFields inputFields = new InputFields(row.getRowNum(), cell.getStringCellValue(),
						cell1.getStringCellValue(), cell2.getStringCellValue());

				listInputFields.add(inputFields);

				// System.out.println("next record --" + i);
			}

			return listInputFields;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean writeFieldintoFile(File output, List<InputFields> listFields) throws IOException {
		FileInputStream file = new FileInputStream(output);
		try (Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<InputFields> iterator = listFields.iterator();
			while (iterator.hasNext()) {
				InputFields inputFields = iterator.next();
				Row row = sheet.getRow(inputFields.getRecordNumber());
				Cell cell = row.createCell(36, CellType.STRING);
				// cell.setCellType(CellType.STRING);
				cell.setCellValue(inputFields.getFieldValue());
			}
			FileOutputStream outFile = new FileOutputStream(output);
			workbook.write(outFile);
			outFile.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
