package ru.teamsource.bankruptservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import javax.validation.Valid;

@Data
@Valid
public class PersonDto {
    @Pattern(regexp = "^(([0-9]{12})|([0-9]{10}))?$")
    private final String inn;
    @Pattern(regexp = "^([0-9]{6})?$")
    private final String passportNumber;
    @Pattern(regexp = "^([0-9]{2}\\s{1}[0-9]{2})?$")
    private final String passportSeries;
    @Pattern(regexp = "^([9]{1}[0-9]{9})?$")
    private String phoneNumber;
    @Pattern(regexp = "^(([0-9]{12})|([0-9]{10}))?$")
    String ogrn;
    @Pattern(regexp = "^(([0-9]{12})|([0-9]{10}))?$")
    String ogrnip;
    @Pattern(regexp = "^([0-9]{2}\\s{1}[0-9]{2})?$")
    String snils;
    @Max(30)
    private final String name;
    @Max(30)
    private final String middleName;
    @Max(30)
    private final String lastName;
    private final String birthdate;
    private final AddressDto addressDto;
}
