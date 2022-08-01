package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String email;
    private String username;
    private UUID personid;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public UUID getPersonId() {
        return personid;
    }
    public void setPersonId(UUID personid) {
        this.personid = personid;
    }

    public UserResponse(UUID id, String email, String username, UUID personid) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.personid = personid;
    }
}
