package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Skill;
import ar.gob.inti.argentinaprograma.miportfolio.repository.SkillRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.ISkillService;

@Service
public class SkillService implements ISkillService {

    @Autowired
    private SkillRepository _repository;

    @Override
    public List<Skill> findAllByProfileId(UUID profileId) throws NotFoundException {
        List<Skill> skills = _repository.findAllByProfileId(profileId);

        if (skills.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return skills;
    }

    @Override
    public Skill findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Skill.class, id));
    }

    @Override
    public Skill create(Skill skill) {
        try {
            return _repository.save(skill);
        } catch (IllegalArgumentException ex) {
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Skill update(UUID id, Skill skill) throws NotFoundException {
        try {
            if (_repository.existsById(id)) {
                _repository.save(skill);
            } else {
                throw new NotFoundException(Skill.class, id);
            }
            return skill;
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
        } else {
            throw new NotFoundException(Skill.class, id);
        }
    }
}