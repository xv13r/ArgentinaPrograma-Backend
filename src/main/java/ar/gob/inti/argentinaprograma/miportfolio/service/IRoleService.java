package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Role;

public interface IRoleService {
    public List<Role> findAll();
    public Role create(Role role);
    public Role findById(UUID id);
    public Role findByName(String name);
    public Role update(UUID id, Role role);
    public void deleteById(UUID id);
}
