package AUT.utilities;

import AUT.constants.CommonConstants;
import AUT.listeners.ReportListeners;
import org.apache.poi.ss.usermodel.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class Utils {

	public static boolean isSuiteRunnable(Xls_Reader xls, String suiteName) {
		boolean isExecutable = false;
		for (int i = 2; i <= xls.getRowCount("Test Suite"); i++) {
			// String suite = xls.getCellData("Test Suite", "TSID", i);
			// String runmode = xls.getCellData("Test Suite", "Runmode", i);

			if (xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)) {
				if (xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}

		}
		xls = null; // release memory
		return isExecutable;

	}

	// returns true if runmode of the test is equal to Y
	public static boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName) {
		boolean isExecutable = false;
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
			// String tcid=xls.getCellData("Test Cases", "TCID", i);
			// String runmode=xls.getCellData("Test Cases", "Runmode", i);
			// System.out.println(tcid +" -- "+ runmode);

			if (xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)) {
				if (xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}
		}

		return isExecutable;

	}

	// return the test data from a test in a 2 dim array
	public static Object[][] getData(Xls_Reader xls, String sheetName) {
		// if the sheet is not present
		if (!xls.isSheetExist(sheetName)) {
			xls = null;
			return new Object[1][0];
		}
		int rows = xls.getRowCount(sheetName);
		int cols = xls.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				// System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
				data[rowNum - 2][colNum] = xls.getCellData(sheetName, colNum, rowNum).trim();

			}
			// System.out.println();
		}
		return data;

	}

	// getData From the last row present in excel
	public static Object[][] getDataFromLastRow(Xls_Reader xls, String sheetName) {
		// if the sheet is not present
		if (!xls.isSheetExist(sheetName)) {
			xls = null;
			return new Object[1][0];
		}
		int rows = xls.getRowCount(sheetName);
		int cols = xls.getColumnCount(sheetName);

		Object[][] data = new Object[1][cols];
		for (int rowNum = rows; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				// System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
				data[0][colNum] = xls.getCellData(sheetName, colNum, rowNum).trim();

			}
			// System.out.println();
		}
		return data;
	}

	// checks RUnmode for dataSet
	public static String[] getDataSetRunmodes(Xls_Reader xlsFile, String sheetName) {
		String[] runmodes = null;
		if (!xlsFile.isSheetExist(sheetName)) {
			xlsFile = null;
			sheetName = null;
			runmodes = new String[1];
			runmodes[0] = "Y";
			xlsFile = null;
			sheetName = null;
			return runmodes;
		}
		runmodes = new String[xlsFile.getRowCount(sheetName) - 1];
		for (int i = 2; i <= runmodes.length + 1; i++) {
			runmodes[i - 2] = xlsFile.getCellData(sheetName, "Runmode", i);
		}
		xlsFile = null;
		sheetName = null;
		return runmodes;

	}

	// update results for a particular data set
	public static void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum, String result) {
		xls.setCellData(testCaseName, "Results", rowNum, result);
	}

	// return the row num for a test
	public static int getRowNum(Xls_Reader xls, String sheetName, String columnName, String rowText) {
		for (int i = 2; i <= xls.getRowCount(sheetName); i++) {
			String tcid = xls.getCellData(sheetName, columnName, i);

			if (tcid.equalsIgnoreCase(rowText)) {
				xls = null;
				return i;
			}

		}

		return -1;
	}

	public static int getRowNumSuites(Xls_Reader xls, String id) {
		for (int i = 2; i <= xls.getRowCount("Test Suite"); i++) {
			String tcid = xls.getCellData("Test Suite", "TSID", i);

			if (tcid.equals(id)) {
				xls = null;
				return i;
			}

		}

		return -1;
	}

	public static int rowCount(Xls_Reader xls, String sheetName) {

		return xls.getRowCount(sheetName);
	}

	public static void addNewSheet(Xls_Reader xls, String name) {

		xls.addSheet(name);

	}

	public static void rmColumn(Xls_Reader xls, String name, int colNum) {

		xls.removeColumn(name, colNum);

	}

	public static int getTotalColumn(Xls_Reader xls, String sheetName) {

		return xls.getColumnCount(sheetName);
	}

	public static void addNewColumn(Xls_Reader xls, String sheetName, String colname) {

		xls.addColumn(sheetName, colname);

	}

	public static String getCoulmnValue(Xls_Reader xls, String sheetName, int colNum, int rowNum) {

		return xls.getCellData(sheetName, colNum, rowNum);

	}

	public static String getRowValue(Xls_Reader xls, String sheetName, String colName, int rowNum) {

		return xls.getCellData(sheetName, colName, rowNum);

	}


	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static Map<String, String[]> loadQuestionnaireMapFromExcel(String filePath, String sheetName) {
		Map<String, String[]> parentToChildren = new HashMap<>();
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				ReportListeners.logStep("fail", "Sheet not found: " + sheetName);
				return parentToChildren;
			}
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row == null) continue;

				Cell parentCell = row.getCell(0); // Parent
				Cell childrenCell = row.getCell(1); // Children

				if (parentCell == null) continue;
				String parent = parentCell.getStringCellValue().trim();
				if (parent.isEmpty()) continue;

				String[] childrenArray;
				if (childrenCell != null && !childrenCell.getStringCellValue().trim().isEmpty()) {
					String childrenStr = childrenCell.getStringCellValue().trim();
					childrenArray = Arrays.stream(childrenStr.split(";"))
							.map(String::trim)
							.filter(s -> !s.isEmpty())
							.toArray(String[]::new);
					ReportListeners.logStep("info",
							"Parent: '" + parent + "' -> Children: " + Arrays.toString(childrenArray));
				} else {
					childrenArray = new String[0];
					ReportListeners.logStep("info",
							"Parent: '" + parent + "' has no children.");
				}

				parentToChildren.put(parent, childrenArray);
			}
			ReportListeners.logStep("pass", "Loaded questionnaire map from Excel successfully.");
		} catch (Exception e) {
			ReportListeners.logStep("fail",
					"ERROR: Exception while reading Excel: " + e.getMessage());
		} finally {
			try { if (workbook != null) workbook.close(); } catch (Exception e) {}
			try { if (fis != null) fis.close(); } catch (Exception e) {}
		}
		return parentToChildren;
	}

	public static List<Map<String, String>> readQuestionnaire() {
		final String EXCEL_PATH = CommonConstants.getTpqqQuestionaireDataFilepath();
		List<Map<String, String>> list = new ArrayList<>();
		FileInputStream fis = null;
		Workbook wb = null;

		try {
			fis = new FileInputStream(EXCEL_PATH);
			wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheetAt(0);
			for (int i = 1; i <= sh.getLastRowNum(); i++) {
				Row row = sh.getRow(i);
				if (row == null) continue;
				Map<String, String> map = new HashMap<>();
				map.put("Parent", getCellValue(row.getCell(0)));
				map.put("Sub", getCellValue(row.getCell(1)));
				map.put("Question", getCellValue(row.getCell(2)));
				list.add(map);
			}
			ReportListeners.logStep("PASS", "Successfully read questionnaire data from Excel: " + EXCEL_PATH);
		} catch (FileNotFoundException e) {
			ReportListeners.logStep("FAIL", "Excel file not found at: " + EXCEL_PATH + " - " + e.getMessage());
		} catch (Exception e) {
			ReportListeners.logStep("FAIL", "Error occurred while reading Excel questionnaire: " + e.getMessage());
		} finally {
			try {
				if (wb != null) wb.close();
				if (fis != null) fis.close();
			} catch (Exception e) {
				ReportListeners.logStep("FAIL", "Error closing Excel resources: " + e.getMessage());
			}
		}
		return list;
	}

	private static String getCellValue(Cell cell) {
		try {
			if (cell == null) return "";
			cell.setCellType(CellType.STRING);
			String value = cell.getStringCellValue().trim();
			ReportListeners.logStep("PASS", "Cell value read: '" + value + "'");
			return value;
		} catch (Exception e) {
			ReportListeners.logStep("FAIL", "Failed to read or convert cell value: " + e.getMessage());
			return "";
		}
	}

}
