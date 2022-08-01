package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Proyect;

public interface IProyectService {
    public List<Proyect> findAllByProfileId(UUID profileId);;
    public Proyect create(Proyect address);
    public Proyect findById(UUID id);
    public Proyect update(UUID id, Proyect address);
    public void deleteById(UUID id);
}