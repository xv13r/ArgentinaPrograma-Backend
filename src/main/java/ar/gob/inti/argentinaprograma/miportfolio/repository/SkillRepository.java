package ar.gob.inti.argentinaprograma.miportfolio.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.gob.inti.argentinaprograma.miportfolio.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {
    List<Skill> findAllByProfileId (UUID profileId);
}