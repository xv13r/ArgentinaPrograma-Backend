package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Education;

public interface IEducationService {
    public List<Education> findAllByProfileId(UUID profileId);
    public Education create(Education address);
    public Education findById(UUID id);
    public Education update(UUID id, Education address);
    public void deleteById(UUID id);
}