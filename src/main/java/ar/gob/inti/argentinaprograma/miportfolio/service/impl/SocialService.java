package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Social;
import ar.gob.inti.argentinaprograma.miportfolio.repository.SocialRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.ISocialService;

@Service
public class SocialService implements ISocialService {
    @Autowired
    private SocialRepository _repository;

    @Override
    public List<Social> findAllByProfileId(UUID profileId) throws NotFoundException {
        List<Social> socials = _repository.findAllByProfileId(profileId);

        if (socials.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }

        return socials;
    }

    @Override
    public Social findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Social.class, id));
    }

    @Override
    public Social create(Social social) {
        try{
            return _repository.save(social);
        }
        catch(IllegalArgumentException ex){
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Social update(UUID id, Social social) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.save(social);
        } else {
            throw new NotFoundException(Social.class, id);
        }
        return social;
    }

    @Override
    public void deleteById(UUID id) {
        _repository.deleteById(id);
    }
}