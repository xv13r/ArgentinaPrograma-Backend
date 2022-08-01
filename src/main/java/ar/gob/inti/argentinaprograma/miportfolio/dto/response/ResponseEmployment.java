package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.util.UUID;

public class ResponseEmployment {
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ResponseEmployment(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
