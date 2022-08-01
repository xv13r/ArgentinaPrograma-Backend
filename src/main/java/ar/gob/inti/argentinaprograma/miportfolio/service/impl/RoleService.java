package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Role;
import ar.gob.inti.argentinaprograma.miportfolio.repository.RoleRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IRoleService;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository _repository;

    @Override
    public List<Role> findAll() throws NotFoundException {
        List<Role> roles = _repository.findAll();

        if (roles.isEmpty()) {

            throw new NotFoundException("No se encontraron datos.");
        }

        return roles;
    }

    @Override
    public Role findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Role.class, id));
    }

    @Override
    public Role findByName(String name) throws NotFoundException {
        Role role = _repository.findByName(name);

        if (role == null) {
            throw new NotFoundException("No se encontraron datos.");
        }
        return role;
    }

    @Override
    public Role create(Role role) {
        try {
            return _repository.save(role);
        } catch (IllegalArgumentException ex) {
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Role update(UUID id, Role role) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.save(role);
        } else {
            throw new NotFoundException(Role.class, id);
        }
        return role;
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
        } else {
            throw new NotFoundException(Role.class, id);
        }
    }
}
