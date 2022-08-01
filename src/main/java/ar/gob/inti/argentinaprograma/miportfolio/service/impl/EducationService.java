package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Education;
import ar.gob.inti.argentinaprograma.miportfolio.repository.EducationRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IEducationService;

@Service
public class EducationService implements IEducationService {

    @Autowired
    private EducationRepository _repository;

    @Override
    public List<Education> findAllByProfileId(UUID profileId) throws NotFoundException {
        List<Education> educations = _repository.findAllByProfileId(profileId);

        if (educations.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return educations;
    }

    @Override
    public Education findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Education.class, id));
    }

    @Override
    public Education create(Education education) {
        try {
            return _repository.save(education);
        } catch (IllegalArgumentException ex) {
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Education update(UUID id, Education education) throws NotFoundException {
        try {
            if (_repository.existsById(id)) {
                _repository.save(education);
            } else {
                throw new NotFoundException(Education.class, id);
            }
            return education;
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
        } else {
            throw new NotFoundException(Education.class, id);
        }
    }
}