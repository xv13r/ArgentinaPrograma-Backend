package ar.gob.inti.argentinaprograma.miportfolio.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.gob.inti.argentinaprograma.miportfolio.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, UUID> {
    List<Education> findAllByProfileId (UUID profileId);
}