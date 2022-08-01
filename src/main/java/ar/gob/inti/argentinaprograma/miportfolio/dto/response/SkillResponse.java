package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.util.UUID;

public class SkillResponse {
    private UUID id;
    private String name;
    private String description;
    private double progress;
    
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
    public double getProgress() {
        return progress;
    }
    public void setProgress(double progress) {
        this.progress = progress;
    }

    public SkillResponse(UUID id, String name, String description, double progress) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.progress = progress;
    }
}
