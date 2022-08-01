package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Proyect;
import ar.gob.inti.argentinaprograma.miportfolio.repository.ProyectRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProyectService;

@Service
public class ProyectService implements IProyectService {

    @Autowired
    private ProyectRepository _repository;

    @Override
    public List<Proyect> findAllByProfileId(UUID profileId) throws NotFoundException {
        List<Proyect> proyects = _repository.findAllByProfileId(profileId);

        if (proyects.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return proyects;
    }

    @Override
    public Proyect findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Proyect.class, id));
    }

    @Override
    public Proyect create(Proyect proyect) {
        try {
            return _repository.save(proyect);
        } catch (IllegalArgumentException ex) {
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Proyect update(UUID id, Proyect proyect) throws NotFoundException {
        try {
            if (_repository.existsById(id)) {
                _repository.save(proyect);
            } else {
                throw new NotFoundException(Proyect.class, id);
            }
            return proyect;
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
        } else {
            throw new NotFoundException(Proyect.class, id);
        }
    }
}