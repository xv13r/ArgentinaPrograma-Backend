package ar.gob.inti.argentinaprograma.miportfolio.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.IdValidation;

@Entity
@Table(name = "educations")
public class Education {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @IdValidation
    private UUID id;

    @Column(nullable = false, length = 64, name = "school")
    private String school;

    @Column(nullable = false, length = 128, name = "career")
    private String career;

    @Column(nullable = false, name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(nullable = true, name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(name = "image")
    private UUID imageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile profile;

    public Education(String school, String career, LocalDate startDate, LocalDate endDate, UUID imageId, Category category) {
        this.school = school;
        this.career = career;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageId = imageId;
        this.category = category;
    }

    public Education() {
    }

    public UUID getId() {
        return id;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}