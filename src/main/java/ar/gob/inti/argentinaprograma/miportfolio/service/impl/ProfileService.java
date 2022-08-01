package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.repository.ProfileRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private ProfileRepository _repository;

    @Override
    public List<Profile> findAll() throws NotFoundException {
        List<Profile> profiles = _repository.findAll();

        if (profiles.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return profiles;
    }

    @Override
    public List<Profile> findAllById(UUID profileId) throws NotFoundException {
        List<Profile> profiles = _repository.findAllById(profileId);

        if (profiles.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return profiles;
    }

    @Override
    public Profile findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Profile.class, id));
    }

    @Override
    public Profile findByUserId(UUID userId) throws NotFoundException {
        return _repository.findByUserId(userId).orElseThrow(() -> new NotFoundException(Profile.class, userId));
    }

    @Override
    public Profile create(Profile profile) {
        try {
            return _repository.save(profile);
        } catch (IllegalArgumentException ex) {
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Profile update(Profile profile) throws ValidationException {
        try {
            return _repository.save(profile);
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
        } else {
            throw new NotFoundException(Profile.class, id);
        }
    }
}