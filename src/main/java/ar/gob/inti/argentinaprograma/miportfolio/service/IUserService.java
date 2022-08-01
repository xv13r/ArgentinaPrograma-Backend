package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.User;

public interface IUserService {
    public List<User> findAll();
    public User create(User user);
    public User findById(UUID id);
    public User update(UUID id, User user);
    public void deleteById(UUID id);
    public Optional<User> findByEmail(String email);
    public Boolean existsByEmail(String email);
}