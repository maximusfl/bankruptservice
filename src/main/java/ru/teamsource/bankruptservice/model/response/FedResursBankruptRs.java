package ru.teamsource.bankruptservice.model.response;
import java.util.List;

public record FedResursBankruptRs(String guid, String type, String categoryCode, List<String> data) {
}