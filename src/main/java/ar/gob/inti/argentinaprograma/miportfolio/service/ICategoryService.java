package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.util.List;
import java.util.UUID;

import ar.gob.inti.argentinaprograma.miportfolio.model.Category;

public interface ICategoryService {
    public List<Category> findAll();
    public Category create(Category category);
    public Category findById(UUID id);
    public Category update(UUID id, Category category);
    public void deleteById(UUID id);
}