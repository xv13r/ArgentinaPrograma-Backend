package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestAddress {

    @NotBlank(message = "Requerido")
    @Size(min = 3, message = "Debe tener al menos 3 caracteres")
    private String street;

    @NotBlank(message = "Requerido")
    private String number;
    
    @NotBlank(message = "Requerido")
    @Size(min = 4, message = "Debe tener al menos 3 caracteres")
    @Size(max = 8, message = "Debe tener como máximo 8 caracteres")
    private String zipCode;
    
    @Size(max = 3, message = "Debe tener al menos 3 caracteres")
    private String floor;
    
    @Size(max = 5, message = "Debe tener al menos 5 caracteres")
    private String apartment;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}