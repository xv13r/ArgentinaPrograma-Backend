package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Address;
import ar.gob.inti.argentinaprograma.miportfolio.repository.AddressRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IAddressService;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository _repository;

    @Override
    public List<Address> findAllByProfileId(UUID profileId) throws NotFoundException {
        List<Address> addresses = _repository.findAllByProfileId(profileId);

        if (addresses.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return addresses;
    }

    @Override
    public Address findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Address.class, id));
    }

    @Override
    public Address create(Address address) throws ValidationException {
        try {
            return _repository.save(address);
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Address update(UUID id, Address address) throws ValidationException, NotFoundException {
        try {
            if (_repository.existsById(id)) {
                _repository.save(address);
            } else {
                throw new NotFoundException(Address.class, id);
            }
            return address;
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
        } else {
            throw new NotFoundException(Address.class, id);
        }
    }
}