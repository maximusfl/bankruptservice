package ru.teamsource.bankruptservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teamsource.bankruptservice.model.PersonData;
import ru.teamsource.bankruptservice.model.response.FedResursBankruptRs;
import ru.teamsource.bankruptservice.dao.PersonDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankruptService {
    private final PersonMapper mapper;
    private final PersonDao personDao;

    public List<FedResursBankruptRs> findAll() {
        List<PersonData> personData = personDao.executePersonDataScript();
        return personData.stream().map(mapper::map).toList();
    }

}
