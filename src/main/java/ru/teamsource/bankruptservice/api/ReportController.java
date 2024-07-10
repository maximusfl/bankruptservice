package ru.teamsource.bankruptservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.teamsource.bankruptservice.model.request.PersonReportRq;
import ru.teamsource.bankruptservice.service.ReportService;
import ru.teamsource.bankruptservice.service.ValidationService;

import static ru.teamsource.bankruptservice.model.Constants.API_V1;

@Controller
@RequestMapping(value = API_V1)
@RequiredArgsConstructor
public class ReportController<T> {
    private final ValidationService validationService;
    private final ReportService reportService;


    @Operation(summary = "Get an excel report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "Not authorized")
    })
    @PostMapping("/report")
    @PreAuthorize("hasRole('ROLE_AUDITOR')")
    public ResponseEntity<Workbook> reportBankrupts(@RequestBody PersonReportRq personReportRq) {
        validationService.validate(personReportRq);
        return new ResponseEntity<>(reportService.report(personReportRq), HttpStatus.OK);
    }
}
