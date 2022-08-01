package ar.gob.inti.argentinaprograma.miportfolio.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.IdValidation;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @IdValidation
    private UUID id;

    @Column(nullable = false, length = 32, name = "name")
    private String name;

    @Column(nullable = false, length = 32, name = "lastname")
    private String lastname;
    
    @Column(nullable = false, name = "birthday", columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd}", timezone="America/Argentina/Buenos_Aires")
    private LocalDate birthday;

    @Column(nullable = false, length = 128, name = "title")
    private String title;
    
    @Column(nullable = false, length = 255, name = "about")
    private String about;

    @Column(name = "avatar_id", updatable = true, nullable = true, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID avatarId;

    @Column(name = "banner_id", updatable = true, nullable = true, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID bannerId;
    
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
     private Set<Social> socials;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Education> educations;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Experience> experiences;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Proyect> proyects;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Skill> skills;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Profile(String name, String lastname, LocalDate birthday, String about, String title) {
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.about = about;
        this.title = title;
    }

    public Profile() {
    }

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

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Social> getSocials() {
        return socials;
    }

    public void setSocials(Set<Social> socials) {
        this.socials = socials;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public Set<Proyect> getProyects() {
        return proyects;
    }

    public void setProyects(Set<Proyect> proyects) {
        this.proyects = proyects;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

//     public void addSocial(Social social) {
// 		this.socials.add(social);
// 	}

//     public void removeSocial(Social social) {
//         this.socials.remove(social);
//     }

//     public void addAddress(Address address) {
// 		this.addresses.add(address);
// 	}

//     public void removeAddress(Address address) {
//         this.addresses.remove(address);
//     }

//     public void addEducation(Education education) {
// 		this.educations.add(education);
// 	}

//     public void removeEducation(Education education) {
//         this.educations.remove(education);
//     }

//     public void addExperience(Experience experience) {
// 		this.experiences.add(experience);
// 	}

//     public void removeExperience(Experience experience) {
//         this.experiences.remove(experience);
//     }

//     public void addSkill(Skill skill) {
// 		this.skills.add(skill);
// 	}

//     public void removeSkill(Skill skill) {
//         this.skills.remove(skill);
//     }

//     public void addProyect(Proyect proyect) {
// 		this.proyects.add(proyect);
// 	}

//     public void removeProyect(Proyect proyect) {
//         this.proyects.remove(proyect);
//     }
}