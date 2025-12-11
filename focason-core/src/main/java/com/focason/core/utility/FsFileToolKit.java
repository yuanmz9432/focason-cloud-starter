package com.focason.core.utility;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FsFileToolKit
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class FsFileToolKit
{

    private static final Logger logger = LoggerFactory.getLogger(FsFileToolKit.class);
    private static final Gson gson = new Gson();

    /**
     * Reads an Excel file and converts it to a JSON object.
     *
     * @param filePath Path to the Excel file.
     * @param sheetNumber Index of the sheet to read (0-based).
     * @param headerRowNumber Row number of the header (0-based).
     * @return JSON object representing the Excel data.
     * @throws RuntimeException If the file cannot be read or is invalid.
     */
    public static JsonObject readExcelAsJson(String filePath, int sheetNumber, int headerRowNumber) {
        try (FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(sheetNumber);
            validateSheet(sheet, headerRowNumber);

            // Extract header and data rows
            Row headerRow = sheet.getRow(headerRowNumber);
            Map<Integer, Map<String, String>> data = extractDataFromSheet(sheet, headerRow, headerRowNumber + 1);

            // Convert data to JSON
            return convertDataToJson(data);
        } catch (Exception e) {
            logger.error("Failed to read Excel file: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to read Excel file", e);
        }
    }

    /**
     * Validates if the sheet and header row are valid.
     *
     * @param sheet The sheet to validate.
     * @param headerRowNumber The row number of the header.
     * @throws IllegalArgumentException If the sheet is empty or the header row is missing.
     */
    private static void validateSheet(Sheet sheet, int headerRowNumber) {
        if (sheet == null || sheet.getLastRowNum() < headerRowNumber) {
            throw new IllegalArgumentException("Sheet is empty or header row is missing");
        }
    }

    /**
     * Extracts data from the sheet starting from the specified row.
     *
     * @param sheet The sheet to extract data from.
     * @param headerRow The header row containing column names.
     * @param startRowNumber The starting row number for data extraction (0-based).
     * @return A map where the key is the row number and the value is a map of column names to cell values.
     */
    private static Map<Integer, Map<String, String>> extractDataFromSheet(Sheet sheet, Row headerRow,
        int startRowNumber) {
        Map<Integer, Map<String, String>> result = new LinkedHashMap<>();
        int rowNum = 1; // 1-based row numbering for output

        for (int i = startRowNumber; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue; // Skip empty rows
            }

            Map<String, String> rowData = extractRowData(row, headerRow);
            result.put(rowNum++, rowData);
        }

        return result;
    }

    /**
     * Extracts data from a single row.
     *
     * @param row The row to extract data from.
     * @param headerRow The header row containing column names.
     * @return A map of column names to cell values.
     */
    private static Map<String, String> extractRowData(Row row, Row headerRow) {
        Map<String, String> rowData = new LinkedHashMap<>();

        for (int j = 0; j < headerRow.getLastCellNum(); j++) {
            String columnName = getCellValueAsString(headerRow.getCell(j), "Column_" + (j + 1));
            String cellValue = getCellValueAsString(row.getCell(j), "");
            rowData.put(columnName, cellValue);
        }

        return rowData;
    }

    /**
     * Converts a cell value to a string.
     *
     * @param cell The cell to convert.
     * @param defaultValue The default value if the cell is null or empty.
     * @return The string value of the cell.
     */
    private static String getCellValueAsString(Cell cell, String defaultValue) {
        if (cell == null) {
            return defaultValue;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                } else {
                    // 如果是整数，去掉小数点
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue);
                    }
                    return String.valueOf(numericValue);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return defaultValue;
        }
    }

    /**
     * Converts the extracted data to a JSON object.
     *
     * @param data The data to convert.
     * @return A JSON object representing the data.
     */
    private static JsonObject convertDataToJson(Map<Integer, Map<String, String>> data) {
        String jsonString = gson.toJson(data);
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

    /**
     * Deletes the specified temporary file from the local directory.
     * This method ensures the temporary file is removed to free up storage space.
     * If the file deletion fails, it logs a warning message.
     *
     * @param filePath The absolute or relative path of the file to be deleted.
     */
    public static void deleteTemporaryFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                logger.info("Temporary file deleted: {}", filePath);
            } else {
                logger.warn("Failed to delete temporary file: {}", filePath);
            }
        } else {
            logger.warn("Temporary file not found: {}", filePath);
        }
    }

    /**
     * 将数据列表导出为Excel字节数组
     * 
     * @param dataList 数据列表
     * @param exportColumnsJson 导出的列配置JSON数组，如 ["warehouseName","warehouseCode"]
     * @param headerNames 可选，自定义列头名称，顺序需与exportColumnsJson一致
     * @param <T> 数据类型
     * @return Excel文件的字节数组
     */
    public static <T> byte[] exportToExcel(List<T> dataList, String exportColumnsJson, List<String> headerNames)
        throws IOException, IllegalArgumentException {

        // 解析JSON数组到字符串数组
        String[] columnFields;
        try {
            columnFields = gson.fromJson(exportColumnsJson, String[].class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Invalid export columns JSON format", e);
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = createHeaderStyle(workbook);

            // 设置列头
            for (int i = 0; i < columnFields.length; i++) {
                Cell cell = headerRow.createCell(i);
                // 如果有自定义列头名称则使用，否则使用字段名作为列头
                String headerName = (headerNames != null && i < headerNames.size())
                    ? headerNames.get(i)
                    : columnFields[i];
                cell.setCellValue(headerName);
                cell.setCellStyle(headerStyle);
            }

            // 填充数据
            CellStyle dataStyle = createDataStyle(workbook);
            int rowNum = 1;

            for (T data : dataList) {
                Row row = sheet.createRow(rowNum++);

                for (int i = 0; i < columnFields.length; i++) {
                    Cell cell = row.createCell(i);
                    try {
                        Object value = getFieldValue(data, columnFields[i]);
                        setCellValue(cell, value);
                        cell.setCellStyle(dataStyle);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new IllegalArgumentException(
                            "Field not found or inaccessible: " + columnFields[i], e);
                    }
                }
            }

            // 自动调整列宽
            for (int i = 0; i < columnFields.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 写入字节数组
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    private static Object getFieldValue(Object obj, String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private static CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private static void setCellValue(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }
}
