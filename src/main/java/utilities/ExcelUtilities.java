package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtilities {

    private static JSONObject testData = new JSONObject();
    private static JSONObject locators = new JSONObject();
    static ConfigUtilities config;
    static LoggerUtilities log;

    public ExcelUtilities( LoggerUtilities log, ConfigUtilities config){
        ExcelUtilities.config = config;
        ExcelUtilities.log = log;
    }

    public static String getCellValue(Cell cell) {
        String value;

        switch (cell.getCellType()) {

            case STRING:
                value = cell.getStringCellValue();
                break;

            case NUMERIC:
                value = String.valueOf(cell.getNumericCellValue());
                break;

            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;

            default:
                value = "";
                break;
        }

        return value;
    }

    public void readTestdata() throws IOException {

        File path = new File(ConfigUtilities.resourceDirectory, "Testdata.xlsx");
        FileInputStream inputStream = new FileInputStream(path.getAbsolutePath());

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheet("Testdata");

        for (Row cells : sheet) {
            XSSFRow row = (XSSFRow) cells;

            Iterator<Cell> cellIterator = row.cellIterator();

            int cellNo = 0;
            JSONObject tempJson = new JSONObject();
            String testcasename = "";

            while (cellIterator.hasNext()) {

                XSSFCell cell = (XSSFCell) cellIterator.next();
                String value = getCellValue(cell);

                if (cellNo == 0) {
                    testcasename = value.toLowerCase();
                }

                if (value.contains("=")) {
                    String[] keyValue = getCellValue(cell).split("=");
                    tempJson.put(keyValue[0].toLowerCase(), keyValue[1]);
                }

                cellNo++;
            }
            testData.put(testcasename, tempJson);
        }

    }

    public String getTestdata(String testdata) throws Exception {
        String value = "";
        try {
            JSONObject json = (JSONObject) testData.get(config.getTestcase().toLowerCase());
            log.info("Able to get testdata "+ json.get(testdata.toLowerCase()));
            value = json.get(testdata.toLowerCase()).toString().replaceAll("[\n\r]", "");
        } catch (Exception e) {
            log.error(e, "Testcase or testdata not found with parameter : " + testdata);
            throw new Exception("Testcase or testdata not found with parameter : " + testdata);
        }
        return value;
    }

    public void readLocators() throws IOException {

        File filePath = new File(System.getProperty("user.dir"));
        File fileDir = new File(filePath, "src/main/resources");
        File path = new File(fileDir, "Testdata.xlsx");
        FileInputStream inputStream = new FileInputStream(path.getAbsolutePath());

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheet("Locators");

        for (Row cells : sheet) {
            XSSFRow row = (XSSFRow) cells;

            Iterator<Cell> cellIterator = row.cellIterator();

            int cellNo = 0;
            JSONObject tempJson = new JSONObject();
            JSONObject tempJson1 = new JSONObject();
            String locatorname = "";

            while (cellIterator.hasNext()) {

                XSSFCell cell = (XSSFCell) cellIterator.next();
                String value = getCellValue(cell);

                switch (cellNo) {
                    case 1:
                        locatorname = value.toLowerCase();
                        break;
                    case 2:
                        tempJson.put("type", value.toLowerCase());
                        break;
                    case 3:
                        tempJson.put("locator", value);
                        break;
                }
                cellNo++;
            }
            locators.put(locatorname, tempJson);
        }
        System.out.println(locators);
    }

    public String[] getLocator(String key) throws Exception {
        String[] value = new String[2];

        JSONObject json = (JSONObject) locators.get(key.toLowerCase());
        value[0] = (String) json.get("type");
        value[1] = (String) json.get("locator");

        if (value[0] == null || value[0] == ""){
            throw new Exception("Locator not found with parameter : "+ key);
        }

        return value;
    }

}
