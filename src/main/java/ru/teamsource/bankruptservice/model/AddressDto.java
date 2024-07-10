package ru.teamsource.bankruptservice.model;

import lombok.Data;

@Data
public class AddressDto {
    private final String street;
    private final String city;
    private final String state;
    private final String building;
    private final String room;
    private final String postalCode;
}