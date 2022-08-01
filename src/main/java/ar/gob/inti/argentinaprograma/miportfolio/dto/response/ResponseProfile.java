package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public class ResponseProfile {
    private UUID id;
    private String name;
    private String lastname;
    private LocalDate birthday;
    private String title;
    private String about;
    private UUID avatarId;
    private UUID bannerId;
    private UUID userId;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public ResponseProfile(UUID id, String name, String lastname, LocalDate birthday, String title, String about,
            UUID avatarId, UUID bannerId, UUID userId) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.title = title;
        this.about = about;
        this.avatarId = avatarId;
        this.bannerId = bannerId;
        this.userId = userId;
    }
}
