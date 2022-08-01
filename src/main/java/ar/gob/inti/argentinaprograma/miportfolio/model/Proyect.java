package ar.gob.inti.argentinaprograma.miportfolio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.IdValidation;

@Entity
@Table(name = "proyects")
public class Proyect {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @IdValidation
    private UUID id;

    @Column(nullable = false, length = 64, name = "name")
    private String name;

    @Column(nullable = false, length = 255, name = "description")
    private String description;

    @Column(nullable = false, length = 255, name = "link")
    private String link;

    @Column(nullable = false, name = "created", columnDefinition = "DATE")
    private LocalDate created;

     @ElementCollection
     private List<String> images;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "profile_id", referencedColumnName = "id")
     @OnDelete(action = OnDeleteAction.CASCADE)
     private Profile profile;

     public Proyect(String name, String description, String link, LocalDate created, List<String> images) {
         this.name = name;
         this.description = description;
         this.link = link;
         this.created = created;
         this.images = images;
    }
    
    public Proyect() {
    }
        
    public UUID getId() {
        return id;
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

    public void addImage(String image){
        if (this.images == null){
            images = new ArrayList<String>();
        }
        images.add(image);
    }

    public void removeImage(String image){
        images.remove(image);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}