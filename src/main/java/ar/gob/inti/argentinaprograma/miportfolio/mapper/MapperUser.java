package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestUser;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.UserResponse;
import ar.gob.inti.argentinaprograma.miportfolio.model.User;

@Component
public class MapperUser {

    public List<UserResponse> toResponseList(List<User> users) {
        List<UserResponse> usersResponse = new ArrayList<>();

        if (users != null) {
            usersResponse = users.stream().map(user -> {
                return new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getProfile() !=  null ? user.getProfile().getId() : null
                );
            }).collect(Collectors.toList());
        }
        return usersResponse;
    }

    public User toEntity(RequestUser userRequest) {
        User user = new User();
        if (userRequest != null) {
            user.setName(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
        }
        return user;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getProfile() !=  null ? user.getProfile().getId() : null
        );
    }
}