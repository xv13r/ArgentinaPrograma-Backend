package ar.gob.inti.argentinaprograma.miportfolio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.gob.inti.argentinaprograma.miportfolio.model.File;

@Repository
public interface FileRepository  extends JpaRepository <File, UUID>{

}