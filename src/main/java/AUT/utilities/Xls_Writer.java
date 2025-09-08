package AUT.utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class Xls_Writer {

	public static String sheetName = System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.xlsx";

	public static void putDataInSheet(String data)
    {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(data);

        XSSFRow row = sheet.getRow(0);
        int columnNumberInWhichNeedToInsertData = -1;

        Iterator<Cell> cellIterator =  row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

//            if(cell.getCellTypeEnum()==CellType.STRING){
//                String text = cell.getStringCellValue();
//                if (text.equals("ReservationNumber")) {
//                    columnNumberOfReservationNumber = cell.getAddress().getColumn();
//                    break;
//                }
//            }
            if(cell.getCellType()==CellType.STRING){
                String text = cell.getStringCellValue();
                if (text.equals("ReservationNumber")) {
                    columnNumberInWhichNeedToInsertData = cell.getAddress().getColumn();
                    break;
                }
            }
        }

        Iterator<Row> iterator = sheet.iterator();

        while(iterator.hasNext()) {
            Row nextRow = iterator.next();
            Cell nextCell = nextRow.getCell(columnNumberInWhichNeedToInsertData);
            if(nextCell == null || nextCell.getStringCellValue().equals(""))
            {
                if(nextCell == null) {
                    nextCell = nextRow.createCell(columnNumberInWhichNeedToInsertData);
                }
                XSSFCellStyle style = workbook.createCellStyle();
                nextCell.setCellStyle(style);
                nextCell.setCellType(CellType.STRING);
                nextCell.setCellValue(data);
                break;
            }
        }

        try {
            fos = new FileOutputStream(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
 
	public static void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {

		// Create an object of File class to open xlsx file
		File file = new File(filePath + "/" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook Workbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			Workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			Workbook = new HSSFWorkbook(inputStream);
		}
		// Read excel sheet by sheet name
		Sheet sheet = Workbook.getSheet(sheetName);
		// Get the current count of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		// Get the first row from the sheet
		Row row = sheet.getRow(0);
		// Create a new row and append it at last of sheet
		Row newRow = sheet.createRow(rowCount + 1);
		// Create a loop over the cell of newly created Row
		for (int j = 0; j < dataToWrite.length; j++) {
			// Fill data in row
			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
		}

		// Close input stream
		inputStream.close();
		// Create an object of FileOutputStream class to create write data in excel file
		FileOutputStream outputStream = new FileOutputStream(file);
		// write data in the excel file
		Workbook.write(outputStream);
		// close output stream
		outputStream.close();
	}
	
	
	public static void writeInSpecificCellandRow(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {

		// Create an object of File class to open xlsx file
		File file = new File(filePath + "/" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook Workbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			Workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			Workbook = new HSSFWorkbook(inputStream);
		}
		// Read excel sheet by sheet name
		Sheet sheet = Workbook.getSheet(sheetName);
		// Get the current count of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		// Get the first row from the sheet
		Row row = sheet.getRow(0);
		// Create a new row and append it at last of sheet
		Row newRow = sheet.createRow(rowCount + 1);
		// Create a loop over the cell of newly created Row
		for (int j = 0; j < dataToWrite.length; j++) {
			// Fill data in row
			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
		}

		// Close input stream
		inputStream.close();
		// Create an object of FileOutputStream class to create write data in excel file
		FileOutputStream outputStream = new FileOutputStream(file);
		// write data in the excel file
		Workbook.write(outputStream);
		// close output stream
		outputStream.close();
	}
	
	public static void writeExcelInCell(Xls_Reader xls,String sheetName, String columname, String dataToWrite, int rowNum)
            throws IOException {
        String value = xls.getCellData(sheetName, columname, rowNum);
      
            boolean result = xls.setCellData(sheetName, columname, rowNum, dataToWrite);
    }
	
	public static void writeInSpecificRow(String filePath, String fileName, String sheetName, String columname, String dataToWrite, int rowNum)
            throws IOException {
        
		File file = new File(filePath + "/" + fileName);

		FileInputStream inputStream = new FileInputStream(file);
		Workbook Workbook = null;
	
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {
			
			Workbook = new XSSFWorkbook(inputStream);
		}
		
		else if (fileExtensionName.equals(".xls")) {
			
			Workbook = new HSSFWorkbook(inputStream);
		}
		
		Sheet sheet = Workbook.getSheet(sheetName);
      
            boolean result = ((Xls_Reader) sheet).setCellData(sheetName, columname, rowNum, dataToWrite);

    }

}