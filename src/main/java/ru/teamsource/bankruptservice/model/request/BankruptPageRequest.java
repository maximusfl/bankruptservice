package ru.teamsource.bankruptservice.model.request;

public record BankruptPageRequest(int limit, int offset, int pageNumber) {}