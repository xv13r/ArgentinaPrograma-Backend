package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.util.UUID;

public class ResponseAddress {
    private UUID id;
    private String street;
    private String number;
    private String zipCode;
    private String floor;
    private String apartment;
    
    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getFloor() {
        return floor;
    }

    public String getApartment() {
        return apartment;
    }
    
    public ResponseAddress(UUID id, String street, String number, String zipCode, String floor, String apartment) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.floor = floor;
        this.apartment = apartment;
    }
}
