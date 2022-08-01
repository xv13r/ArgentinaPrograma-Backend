package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Skill;

public interface ISkillService {
    public List<Skill> findAllByProfileId(UUID profileId);;
    public Skill create(Skill skill);
    public Skill findById(UUID id);
    public Skill update(UUID id, Skill skill);
    public void deleteById(UUID id);
}