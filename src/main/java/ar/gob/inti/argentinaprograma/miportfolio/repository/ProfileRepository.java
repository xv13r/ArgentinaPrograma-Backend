package ar.gob.inti.argentinaprograma.miportfolio.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository <Profile, UUID>{
    List<Profile> findAllById (UUID id);
    Optional<Profile> findById(UUID id);
    Optional<Profile> findByUserId(UUID userId);
}