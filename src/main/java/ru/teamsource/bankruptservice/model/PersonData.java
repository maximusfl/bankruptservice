package ru.teamsource.bankruptservice.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PersonData {
    private String inn;
    private String guid;
    private String deriveType;
    private String deriveCategoryCode;
    private int totalCreditScore;
    private int totalApplications;
    private BigDecimal totalCreditRequested;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String employmentStatus;
    private BigDecimal annualIncome;
}