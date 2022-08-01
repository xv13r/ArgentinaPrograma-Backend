package ar.gob.inti.argentinaprograma.miportfolio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, UUID> {

}