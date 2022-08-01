package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Experience;
import ar.gob.inti.argentinaprograma.miportfolio.repository.ExperienceRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IExperienceService;

@Service
public class ExperienceService implements IExperienceService {

    @Autowired
    private ExperienceRepository _repository;

    @Override
    public List<Experience> findAllByProfileId(UUID profileId) throws NotFoundException {
        List<Experience> experiences = _repository.findAllByProfileId(profileId);

        if (experiences.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return experiences;
    }

    @Override
    public Experience findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Experience.class, id));
    }

    @Override
    public Experience create(Experience experience) {
        try {
            return _repository.save(experience);
        } catch (IllegalArgumentException ex) {
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Experience update(UUID id, Experience experience) throws NotFoundException {
        try {
            if (_repository.existsById(id)) {
                _repository.save(experience);
            } else {
                throw new NotFoundException(Experience.class, id);
            }
            return experience;
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
        } else {
            throw new NotFoundException(Experience.class, id);
        }
    }
}