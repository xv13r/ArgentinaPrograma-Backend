package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RequestProfile {

    @NotBlank(message = "Requerido")
    @Size(min = 3, message = "Debe tener al menos 3 caracteres")
    @Size(max = 32, message = "Debe tener como máximo 32 caracteres")
    private String name;

    @NotBlank(message = "Requerido")
    @Size(min = 3, message = "Debe tener al menos 3 caracteres")
    @Size(max = 32, message = "Debe tener como máximo 32 caracteres")
    private String lastname;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @NotBlank(message = "Requerido")
    private String about;

    @NotBlank(message = "Requerido")
    private String title;

    private UUID avatarId;

    private UUID bannerId;

    private UUID userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(UUID avatarId) {
        this.avatarId = avatarId;
    }

    public UUID getBannerId() {
        return bannerId;
    }

    public void setBannerId(UUID bannerId) {
        this.bannerId = bannerId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}