package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Role;
import ar.gob.inti.argentinaprograma.miportfolio.model.User;
import ar.gob.inti.argentinaprograma.miportfolio.repository.RoleRepository;
import ar.gob.inti.argentinaprograma.miportfolio.repository.UserRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Override
    public List<User> findAll() {
        List<User> users = _userRepository.findAll();

        if (users.isEmpty()) {
            throw new NotFoundException("No se encontraron datos.");
        }
        return users;
    }

    @Override
    public User findById(UUID id) throws NotFoundException {
        return _userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return _userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return _userRepository.existsByEmail(email);
    }

    @Override
    public User create(User user) throws ValidationException {
        try {
            if (!_userRepository.existsByEmail(user.getEmail())) {

                String rawPassword = user.getPassword();
                String encodedPassword = _passwordEncoder.encode(rawPassword);
                user.setPassword(encodedPassword);
                if (user.getRoles().isEmpty()) {
                    Role role = _roleRepository.findByName(RoleEnum.ROLE_USER);
                    if (role == null) {
                        role = _roleRepository.save(new Role(RoleEnum.ROLE_USER));
                    }
                    user.addRole(role);
                }
                return _userRepository.save(user);
            } else {
                throw new ValidationException("Correo electronico ya existe.");
            }
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public User update(UUID id, User user) throws NotFoundException {
        try {
            if (_userRepository.existsById(id)) {
                user.setPassword(_passwordEncoder.encode(user.getPassword()));
                _userRepository.save(user);
            } else {
                throw new NotFoundException(User.class, id);
            }
            return user;
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        User userDelete = _userRepository.getById(id);
        if (userDelete != null) {
            userDelete.getRoles().forEach(role -> {
                Set<User> updateUser = role.getUsers()
                        .stream()
                        .filter(user -> !user.equals(userDelete))
                        .collect(Collectors.toSet());
                role.setUsers(updateUser);
                _roleRepository.save(role);
            });
            userDelete.setRoles(null);
            _userRepository.deleteById(id);
        } else {
            throw new NotFoundException(User.class, id);
        }
    }
}