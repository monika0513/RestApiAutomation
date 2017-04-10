package com.wbl.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static Object[][] data = null;
	
	public static Object[][] getData()
	{
		FileInputStream fin;
		XSSFWorkbook book;
		Sheet sheet;
		int rows = 0;
		int columns = 2;
		int count = 0;

		try {
			fin = new FileInputStream(new File("restdata.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		try {
			book = new XSSFWorkbook(fin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		sheet = book.getSheetAt(0);
		rows = sheet.getLastRowNum();
		
		data = new Object[rows][columns];
				
		for (int i=1; i<rows;i++)
		{
			Row row = sheet.getRow(i);
			Iterator <Cell> cell = row.cellIterator();

			while(cell.hasNext());
			{
				Cell c = cell.next();
				data[i][count] = c.getStringCellValue();
				count++;
			}
		}

		try {
			book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}


