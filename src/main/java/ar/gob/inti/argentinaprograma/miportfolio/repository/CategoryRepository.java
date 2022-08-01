package ar.gob.inti.argentinaprograma.miportfolio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.gob.inti.argentinaprograma.miportfolio.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository <Category, UUID>{
    
}