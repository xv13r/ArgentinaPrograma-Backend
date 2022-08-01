package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Category;

public class ResponseEducation {
    private UUID id;
    private String school;
    private String career;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID imageId;
    private Category category;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getCareer() {
        return career;
    }
    public void setCareer(String career) {
        this.career = career;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public UUID getImageId() {
        return imageId;
    }
    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public ResponseEducation(UUID id, String school, String career, LocalDate startDate,
            LocalDate endDate, UUID imageId, Category category) {
        this.id = id;
        this.school = school;
        this.career = career;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageId = imageId;
        this.category = category;
    }
}
