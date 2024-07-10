package ru.teamsource.bankruptservice.service;

import org.apache.poi.ss.usermodel.Workbook;
import ru.teamsource.bankruptservice.model.request.PersonReportRq;

public interface ReportService {
    Workbook report(PersonReportRq personReportRq);

}
