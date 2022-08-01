package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Address;

public interface IAddressService {
    public List<Address> findAllByProfileId(UUID profileId);;
    public Address create(Address address);
    public Address findById(UUID id);
    public Address update(UUID id, Address address);
    public void deleteById(UUID id);
}