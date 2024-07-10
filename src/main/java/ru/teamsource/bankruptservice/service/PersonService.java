package ru.teamsource.bankruptservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teamsource.bankruptservice.model.response.FedResursBankruptRs;
import ru.teamsource.bankruptservice.repository.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public void saveForAudit(List<FedResursBankruptRs> personsWithVoilations) {
        personRepository.saveAll(
                personsWithVoilations.stream().map(personMapper::mapToPerson).toList()
        );
    }
}
