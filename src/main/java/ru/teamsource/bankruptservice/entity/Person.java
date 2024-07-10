package ru.teamsource.bankruptservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@ToString
@Setter
@Getter
@EqualsAndHashCode
public class Person {
    @Id
    @GeneratedValue(generator = "person_seq_gen")
    @SequenceGenerator(name = "person_seq_gen", schema = "clients", sequenceName = "person_seq")
    private String inn;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "passport_series")
    private String passportSeries;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String ogrn;
    private String ogrnip;
    private String snils;
    private String name;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "total_credit_score")
    private int totalCreditScore;
    @Column(name = "total_applications")
    private int totalApplications;
    @Column(name = "total_credit_requested")
    private int totalCreditRequested;
    @Column(name = "employment_status")
    private String employeeStatus;
    @Column(name = "annual_income")
    private BigDecimal annualIncome;
}