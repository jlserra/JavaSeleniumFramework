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

    public static JSONObject testData = new JSONObject();
    public static JSONObject locators = new JSONObject();
    public static ConfigUtilities config;
    public static LoggerUtilities log;

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

        File path = new File(config.resourceDirectory, "Testdata.xlsx");
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

    public String getTestdata(String testdata) {
        String value = "";
        try {
            JSONObject json = (JSONObject) testData.get(config.getTestcase().toLowerCase());
            log.info("Able to get testdata "+ json.get(testdata.toLowerCase()));
            value = json.get(testdata.toLowerCase()).toString().replaceAll("[\n\r]", "");
        } catch (Exception e) {
            System.out.println("Testcase or testdata not found with parameter : " + testdata);
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
            JSONObject tempJson2 = new JSONObject();
            String locatorname = "";

            while (cellIterator.hasNext()) {

                XSSFCell cell = (XSSFCell) cellIterator.next();
                String value = getCellValue(cell);

                switch (cellNo) {
                    case 1:
                        locatorname = value.toLowerCase();
                        break;
                    case 2:
                        tempJson1.put("type", value);
                        break;
                    case 3:
                        tempJson1.put("locator", value);
                        tempJson.put("android", tempJson1);
                        break;
                    case 4:
                        tempJson2.put("type", value);
                        break;
                    case 5:
                        tempJson2.put("locator", value);
                        tempJson.put("ios", tempJson2);
                        break;
                    default:
                        break;
                }

                cellNo++;
            }
            locators.put(locatorname, tempJson);
        }

    }

    String[] getLocator(String key) {
        String[] value = new String[2];

        JSONObject json = (JSONObject) locators.get(key.toLowerCase());
        JSONObject json1 = (JSONObject) json.get(config.getPlatform().toLowerCase());
        value[0] = (String) json1.get("type");
        value[1] = (String) json1.get("locator");

        return value;
    }

}
