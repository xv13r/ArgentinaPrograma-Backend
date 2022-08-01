package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Social;

public interface ISocialService {
    public List<Social> findAllByProfileId(UUID profileId);
    public Social create(Social category);
    public Social findById(UUID id);
    public Social update(UUID id, Social category);
    public void deleteById(UUID id);
}