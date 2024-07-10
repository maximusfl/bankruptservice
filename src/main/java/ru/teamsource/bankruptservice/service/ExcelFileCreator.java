package ru.teamsource.bankruptservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.teamsource.bankruptservice.model.response.FedResursBankruptRs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class ExcelFileCreator {

    public Workbook createExcelFile(List<FedResursBankruptRs> dataList, String fileName) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(fileName)) {
            Sheet sheet = workbook.createSheet("Bankruptcy Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("GUID");
            headerRow.createCell(1).setCellValue("Type");
            headerRow.createCell(2).setCellValue("Category Code");
            headerRow.createCell(3).setCellValue("Data");

            int rowNum = 1;
            for (FedResursBankruptRs item : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(item.guid());
                row.createCell(1).setCellValue(item.type());
                row.createCell(2).setCellValue(item.categoryCode());
                String dataString = String.join(", ", item.data());
                row.createCell(3).setCellValue(dataString);
            }

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fileOut);
            return workbook;
        } catch (IOException e) {
            String msg = String.format("Error while creating excel: %s", e);
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }
}