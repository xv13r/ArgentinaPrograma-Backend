package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.FileSize;
import ar.gob.inti.argentinaprograma.miportfolio.annotations.ImageTypeList;

public class RequestProyect {

    @NotBlank(message = "Requerido")
    private String name;

    @NotBlank(message = "Requerido")
    private String description;

    //@ImageTypeList(type = {"png", "jpg", "jpeg"}, message = "Solo se permiten imágenes del tipo {type}")
    // @FileSize(maxSizeInMB = 10, message = "Excede el tamaño máximo del archivo, permitido {maxSizeInMB}Mb")
    private List<MultipartFile> images;
    
    @NotBlank(message = "Requerido")
    private String link;
    
    @NotNull(message = "Requerido")
    @Past(message="Fecha de proyecto de ser menor a hoy")  
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate created;

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

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
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
}