package ru.teamsource.bankruptservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.teamsource.bankruptservice.model.request.BankruptPageRequest;
import ru.teamsource.bankruptservice.model.response.FedResursBankruptRs;
import ru.teamsource.bankruptservice.service.BankruptService;
import ru.teamsource.bankruptservice.service.HadoopPersonProcessing;
import ru.teamsource.bankruptservice.service.ValidationService;

import java.util.List;

import static ru.teamsource.bankruptservice.model.Constants.API_V1;

@Controller
@RequestMapping(value = API_V1 + "/bankruptcy")
@RequiredArgsConstructor
public class BankruptcyController {
    private final BankruptService bankruptService;
    private final HadoopPersonProcessing hadoopPersonProcessing;
    private final ValidationService validationService;

    @Operation(summary = "Get bankrupts as view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "Not authorized")
    })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_AUDITOR')")
    public String bankruptcyData(Model model, @RequestBody BankruptPageRequest request) {
        validationService.validate(request);
        List<FedResursBankruptRs> bankruptcyList = bankruptService.findAll();
        model.addAttribute("bankruptcyList", bankruptcyList);
        return "bankruptcy";
    }

    @GetMapping
    @Operation(summary = "Generate statistic for bankrupts")
    @PreAuthorize("hasRole('ROLE_AUDITOR')")
    public ResponseEntity bankruptcyAnalytic() {
        hadoopPersonProcessing.runPersonDataProcess();
        return new ResponseEntity(HttpStatus.OK);
    }
}