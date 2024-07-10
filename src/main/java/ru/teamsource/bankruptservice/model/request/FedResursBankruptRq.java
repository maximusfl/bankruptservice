package ru.teamsource.bankruptservice.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FedResursBankruptRq {
    String type;
    String name;
    String ogrn;
    String ogrnip;
    String inn;
    String snils;
    String birthdate;
    int offset;
    int limit;
}
