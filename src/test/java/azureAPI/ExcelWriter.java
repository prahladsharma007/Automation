package azureAPI;

import AUT.utilities.RandomStringGenerator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelWriter {

    private static final String FILE_PATH = System.getProperty("user.dir")+"/study.xls";
    private static final String FILE_PATH_1 = System.getProperty("user.dir")+"/study1.xls";


    public static List<String> trialNumberArray = new ArrayList<>();
    public static List<String> personalID = new ArrayList<>(Arrays.asList("HARSHVI1", "SURESAR2", "CHAOUSU1"));
    public static List<String> roleName = new ArrayList<>(Arrays.asList("Team Lead", "Clinical Data Scientist 1 (CDS)", "Clinical Trial Lead", "Team Member 2", "Clinical Trial Developer", "Clinical Trial Manager"));




    public static void writeRow(int rowIndex, int firstSheetRow, int secondSheetRow) throws IOException {
//        if (data.length != 8) {
//            throw new IllegalArgumentException("Exactly 8 columns of data required.");
//        }

        Map<Integer, String[]> mapData = new HashMap<>();
//        int rowCount =2;
        for(int i=1;i<=firstSheetRow;i++){
            String trialNumber = RandomStringGenerator.generateRandomString();
            trialNumberArray.add(trialNumber);
            String projectCode = trialNumber.substring(0,5) + trialNumber.substring(8,11);
            String rowsData[] = {Integer.toString(i),
                    String.format("\'%s',",RandomStringGenerator.randomNumeric(5)),
                    String.format("\'%s',",trialNumber),
                    "'Active',",
                    String.format("\'%s',",projectCode),
                    "'SURESAR2@gmail.net',",
            };
            mapData.put(i,rowsData);
        }

        Workbook workbook;
        Sheet sheet;

        File file = new File(FILE_PATH);
        if (file.exists()) {
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheetAt(0);
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Sheet1");
        }

//        Row row = sheet.getRow(rowIndex);
//        if (row == null) row = sheet.createRow(rowIndex);

        for(Map.Entry<Integer,String[]> entry : mapData.entrySet()){
            Row row = sheet.getRow(entry.getKey());
        if (row == null) row = sheet.createRow(entry.getKey());
            String[] values = entry.getValue();
            int mapsize = entry.getKey();
            for (int i = 0; i < 6; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(values[i]);
            }
        }


        // Autosize columns based on content
        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }

        file = new File(FILE_PATH);
        FileOutputStream out = new FileOutputStream(FILE_PATH);
        workbook.write(out);
        out.close();
        workbook.close();

        Random random = new Random();

        Map<Integer, String[]> secondSheetMapData = new HashMap<>();
//        rowCount =10;
        for(int i=1;i<=secondSheetRow;i++){
//            String trialNumber = RandomStringGenerator.generateRandomString();
//            trialNumberArray.add(trialNumber);
//            String projectCode = trialNumber.substring(0,5) + trialNumber.substring(8,11);
            int randomIndex = random.nextInt(trialNumberArray.size());
            String randomTrialNumber = trialNumberArray.get(randomIndex);
            randomIndex = random.nextInt(personalID.size());
            String randomPersonalName = personalID.get(randomIndex);
            randomIndex = random.nextInt(roleName.size());
            String randomRoleName = roleName.get(randomIndex);
            String rowsData[] = {Integer.toString(i),
                    RandomStringGenerator.randomNumeric(5),
                    randomPersonalName,
                    "'2025-05-01',",
                    "'2025-12-31',",
                    randomRoleName,
                    "'SURESAR2@gmail.net',",
            };
            secondSheetMapData.put(i,rowsData);
        }

        file = new File(FILE_PATH_1);
        if (file.exists()) {
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheetAt(0);
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Sheet1");
        }

//        Row row = sheet.getRow(rowIndex);
//        if (row == null) row = sheet.createRow(rowIndex);

        for(Map.Entry<Integer,String[]> entry : secondSheetMapData.entrySet()){
            Row row = sheet.getRow(entry.getKey());
            if (row == null) row = sheet.createRow(entry.getKey());
            String[] values = entry.getValue();
            int mapsize = entry.getKey();
            for (int i = 0; i < 7; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(values[i]);
            }
        }


        // Autosize columns based on content
        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }

        out = new FileOutputStream(FILE_PATH_1);
        workbook.write(out);
        out.close();
        workbook.close();

        System.out.println("Row " + rowIndex + " written successfully.");
    }

    public static void main(String[] args) throws IOException {
//        writeRow(2, rowData); // Writing to 3rd row (0-based indexing)
        writeRow(2, 10, 50);
    }
}

