package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;
import ar.gob.inti.argentinaprograma.miportfolio.repository.EmploymentRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IEmploymentService;

@Service
public class EmploymentService implements IEmploymentService {
    @Autowired
    private EmploymentRepository _repository;

    @Override
    public List<Employment> findAll() throws NotFoundException {
        List<Employment> employments = _repository.findAll();

        if (employments.isEmpty()) {

            throw new NotFoundException("No se encontraron datos.");
        }

        return employments;
    }

    @Override
    public Employment findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(()  -> new NotFoundException(Employment.class, id));
    }

    @Override
    public Employment create(Employment employment) {
        try{
            return _repository.save(employment);
        }
        catch(IllegalArgumentException ex){
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Employment update(UUID id, Employment employment) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.save(employment);
        } else {
            throw new NotFoundException(Employment.class, id);
        }
        return employment;
    }

    @Override
    public void deleteById(UUID id) {
        _repository.deleteById(id);
    }
}