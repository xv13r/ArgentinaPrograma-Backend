package ar.gob.inti.argentinaprograma.miportfolio.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.IdValidation;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @IdValidation
    private UUID id;

    @Column(nullable = false, length = 64, name = "street")
    private String street;

    @Column(nullable = false, length = 7, name = "number")
    private String number;

    @Column(nullable = false, length = 8, name = "postalcode")
    private String zipCode;

    @Column(length = 3, name = "floor")
    private String floor;

    @Column(length = 5, name = "apartment")
    private String apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile profile;

    public Address() {
    }

    public Address(String street, String number, String zipCode, String floor, String apartment) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.floor = floor;
        this.apartment = apartment;
    }

    public UUID getId() {
        return id;
    }

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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}