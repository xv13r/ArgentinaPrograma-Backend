package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;

public interface IEmploymentService {
    public List<Employment> findAll();
    public Employment create(Employment employment);
    public Employment findById(UUID id);
    public Employment update(UUID id, Employment employment);
    public void deleteById(UUID id);
}