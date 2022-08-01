package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.FileSize;
import ar.gob.inti.argentinaprograma.miportfolio.annotations.IdValidation;
import ar.gob.inti.argentinaprograma.miportfolio.annotations.ImageType;

public class RequestExperience {

    @NotBlank(message = "Requerido")
    private String company;

    @NotBlank(message = "Requerido")
    private String description;
    
    @NotNull(message = "Requerido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    
    @ImageType(type = {"png", "jpg", "jpeg"}, message = "Solo se permiten imágenes del tipo {type}")
    @FileSize(maxSizeInMB = 10, message = "Excede el tamaño máximo del archivo, permitido {maxSizeInMB}Mb")
    private MultipartFile image;

    private RequestEmployment employment;

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public RequestEmployment getEmployment() {
        return employment;
    }

    public void setEmploymentId(RequestEmployment employment) {
        this.employment = employment;
    }
}