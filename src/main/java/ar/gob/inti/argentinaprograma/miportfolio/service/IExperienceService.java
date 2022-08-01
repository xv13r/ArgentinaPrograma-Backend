package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Experience;

public interface IExperienceService {
    public List<Experience> findAllByProfileId(UUID profileId);;
    public Experience create(Experience address);
    public Experience findById(UUID id);
    public Experience update(UUID id, Experience address);
    public void deleteById(UUID id);
}