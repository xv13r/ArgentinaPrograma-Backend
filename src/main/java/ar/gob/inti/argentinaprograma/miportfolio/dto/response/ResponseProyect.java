package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ResponseProyect {
    private UUID id;
    private String name;
    private String description;
    private String link;
    private LocalDate created;
    private List<String> images;

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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public LocalDate getCreated() {
        return created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }

    public ResponseProyect(UUID id, String name, String description, String link, LocalDate created, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.created = created;
        this.images = images;
    }
}
