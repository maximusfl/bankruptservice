package ru.teamsource.bankruptservice.model.request;

import lombok.Builder;
import lombok.Data;
import ru.teamsource.bankruptservice.model.PersonDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PersonReportRq {
    private final List<PersonDto> personDtoList;
    private final String rqUid;
    private final LocalDateTime from;
    private final LocalDateTime to;
}
