package ru.teamsource.bankruptservice.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import ru.teamsource.bankruptservice.model.request.PersonReportRq;
import ru.teamsource.bankruptservice.model.response.FedResursBankruptRs;
import ru.teamsource.bankruptservice.service.fedresurs.FedResursService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonReportService implements ReportService {
    private final FedResursService bankruptService;
    private final ExcelFileCreator excelFileCreator;
    private final PersonService personService;
    private static final String FILE_PREFIX = "person_bankrupts_";

    @Override
    public Workbook report(PersonReportRq personReportRq) {
        List<FedResursBankruptRs> personsWithVoilations = bankruptService
                .getPersonsWithVoilations(personReportRq.getPersonDtoList());
        personService.saveForAudit(personsWithVoilations);
        return excelFileCreator
                .createExcelFile(personsWithVoilations, FILE_PREFIX + LocalDateTime.now());
    }
}
