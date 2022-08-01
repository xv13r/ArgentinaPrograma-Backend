package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;

public interface IProfileService {
    public List<Profile> findAll();
    public List<Profile> findAllById(UUID profileId);
    public Profile create(Profile profile);
    public Profile findById(UUID id);
    public Profile findByUserId(UUID userId);
    public Profile update(Profile profile);
    public void deleteById(UUID id);
}