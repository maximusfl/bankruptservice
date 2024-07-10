package ru.teamsource.bankruptservice.service;

import org.springframework.stereotype.Component;
import ru.teamsource.bankruptservice.entity.Person;
import ru.teamsource.bankruptservice.model.PersonData;
import ru.teamsource.bankruptservice.model.response.FedResursBankruptRs;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Collections;
import java.util.Date;

@Component
public class PersonMapper {
    public FedResursBankruptRs map(PersonData personData) {
        String guid = deriveGuidFromPersonData(personData);
        String type = deriveTypeFromPersonData(personData);
        String categoryCode = deriveCategoryCodeFromPersonData(personData);
        return new FedResursBankruptRs(guid, type, categoryCode, Collections.EMPTY_LIST);
    }

    private String deriveGuidFromPersonData(PersonData personData) {
        return personData.getGuid();
    }

    private String deriveTypeFromPersonData(PersonData personData) {
        return personData.getDeriveType();
    }

    private String deriveCategoryCodeFromPersonData(PersonData personData) {
        return personData.getDeriveCategoryCode();
    }

    public Person mapPersonDataToPerson(PersonData personData) {
        Person person = new Person();
        person.setInn(personData.getInn());
        person.setLastName(personData.getLastName());
        person.setTotalCreditScore(personData.getTotalCreditScore());
        person.setTotalApplications(personData.getTotalApplications());
        person.setAnnualIncome(personData.getAnnualIncome());
        person.setTotalCreditRequested(personData.getTotalCreditRequested().intValue());
        person.setBirthdate(convertDateToLocalDate(personData.getDateOfBirth()));
        person.setName(personData.getFirstName());
        person.setEmployeeStatus(personData.getEmploymentStatus());
        return person;
    }

    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Person mapToPerson(FedResursBankruptRs fedResursBankruptRs) {
        Person person = new Person();

        person.setInn(fedResursBankruptRs.getGuid());
        if (fedResursBankruptRs.getData() != null && !fedResursBankruptRs.getData().isEmpty()) {
            person.setPassportNumber(fedResursBankruptRs.getData().get(0)); // Assuming first element is passport number
            person.setPassportSeries(fedResursBankruptRs.getData().get(1)); // Assuming second element is passport series
        }
        person.setBirthdate(LocalDate.now());
        person.setTotalCreditScore(0);
        person.setTotalApplications(0);
        person.setTotalCreditRequested(0);


        return person;
    }

}