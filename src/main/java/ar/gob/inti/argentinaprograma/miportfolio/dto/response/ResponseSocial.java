package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.util.UUID;

public class ResponseSocial {
    private UUID id;
    private String name;
    private String url;

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
    public String getUrl() {
        return url;
    }
    public void setUr(String url) {
        this.url = url;
    }

    public ResponseSocial(UUID id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
