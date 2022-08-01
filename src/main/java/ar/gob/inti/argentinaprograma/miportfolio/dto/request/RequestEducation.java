package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.FileSize;
import ar.gob.inti.argentinaprograma.miportfolio.annotations.IdValidation;
import ar.gob.inti.argentinaprograma.miportfolio.annotations.ImageType;

public class RequestEducation {

    @NotBlank(message = "Requerido")
    @Size(min = 3, message = "Debe tener al menos 3 caracteres")
    @Size(max = 32, message = "Debe tener como máximo 32 caracteres")
    @Pattern(regexp = "[a-záéíóúñA-ZÁÉÍÓÚÑ0-9\\s]*", message = "Debe tener solo números o caracteres")
    private String school;

    @NotBlank(message = "Requerido")
    @Size(min = 3, message = "Debe tener al menos 3 caracteres")
    @Size(max = 48, message = "Debe tener como máximo 48 caracteres")
    @Pattern(regexp = "[a-záéíóúñA-ZÁÉÍÓÚÑ0-9\\s]*", message = "Debe tener solo números o caracteres")
    private String career;
    
    @NotNull(message = "Requerido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    
    // @ImageType(type = {"png", "jpg", "jpeg"}, message = "Solo se permiten imágenes del tipo {type}")
    // @FileSize(maxSizeInMB = 10, message = "Excede el tamaño máximo del archivo, permitido {maxSizeInMB}Mb")
    private UUID imageId;

    private RequestCategory category;

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

    public RequestCategory getCategory() {
        return category;
    }

    public void setCategory(RequestCategory category) {
        this.category = category;
    }
}