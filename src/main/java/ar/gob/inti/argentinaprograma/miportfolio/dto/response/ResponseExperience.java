package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;

public class ResponseExperience {
    private UUID id;
    private String company;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String image;
    private Employment employment;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Employment getEmployment() {
        return employment;
    }
    public void setEmployment(Employment employment) {
        this.employment = employment;
    }

    public ResponseExperience(UUID id, String company, String description, LocalDate startDate, LocalDate endDate, String image, Employment employment) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.employment = employment;
    }
}
