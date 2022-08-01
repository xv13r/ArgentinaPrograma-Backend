package ar.gob.inti.argentinaprograma.miportfolio.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.gob.inti.argentinaprograma.miportfolio.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAllByProfileId (UUID profileId);
    boolean existsByProfileId(UUID profileId);
}