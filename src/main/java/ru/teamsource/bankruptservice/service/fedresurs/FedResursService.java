package ru.teamsource.bankruptservice.service.fedresurs;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.teamsource.bankruptservice.model.PersonDto;
import ru.teamsource.bankruptservice.model.request.FedResursBankruptRq;
import ru.teamsource.bankruptservice.model.response.FedResursBankruptRs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class FedResursService {
    @Value("fedresurs-url")
    private String fedResursUrl;
    private final FedResursRequestMapper mapper;
    private final FedResursClient fedResursClient;
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @SneakyThrows
    public List<FedResursBankruptRs> getPersonsWithVoilations(List<PersonDto> personDtos) {
        List<BankruptFetchTask> tasks = createFetchTasks(personDtos);
        List<Future<ResponseEntity<FedResursBankruptRs>>> futureList =
                executorService.invokeAll(tasks);
        return fromFuture(futureList);

    }

    @RequiredArgsConstructor
    public class BankruptFetchTask implements Callable<ResponseEntity<FedResursBankruptRs>> {
        private final FedResursBankruptRq fedResursBankruptRq;

        @Override
        public ResponseEntity<FedResursBankruptRs> call() {
            return fedResursClient.post(
                    fedResursUrl,
                    fedResursBankruptRq,
                    FedResursBankruptRs.class);
        }
    }

    @SneakyThrows
    private List<FedResursBankruptRs> fromFuture(List<Future<ResponseEntity<FedResursBankruptRs>>> futures) {
        boolean allDone = false;
        List<FedResursBankruptRs> responses = new ArrayList<>();
        while (!allDone) {
            for (Future<ResponseEntity<FedResursBankruptRs>> future : futures) {
                if (future.isDone()) {
                    responses.add(future.get().getBody());
                }
            }
            allDone = futures.stream().allMatch(Future::isDone);
        }
        return responses;
    }

    private List<BankruptFetchTask> createFetchTasks(List<PersonDto> personDtos) {
        return personDtos.stream()
                .map(this::createRq)
                .map(BankruptFetchTask::new)
                .toList();
    }

    private FedResursBankruptRq createRq(PersonDto personDto) {
        return mapper.map(personDto);
    }
}
