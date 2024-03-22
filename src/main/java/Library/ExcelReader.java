package Library;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static List<String> ReadExcel(String filename) {
        List<String> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filename);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    dataList.add(cell.toString());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла Excel: " + e.getMessage());
            return new ArrayList<>();
        }

        return dataList;
    }
}
