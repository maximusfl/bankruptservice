package ru.teamsource.bankruptservice.service.fedresurs;

import org.springframework.stereotype.Component;
import ru.teamsource.bankruptservice.model.PersonDto;
import ru.teamsource.bankruptservice.model.request.FedResursBankruptRq;

@Component
public class FedResursRequestMapper {
    public FedResursBankruptRq map(PersonDto personDto) {
        return FedResursBankruptRq.builder()
                .name(personDto.getName())
                .inn(personDto.getInn())
                .birthdate(personDto.getBirthdate())
                .snils(personDto.getSnils())
                .ogrn(personDto.getOgrn())
                .limit(100)
                .build();
    }
}
