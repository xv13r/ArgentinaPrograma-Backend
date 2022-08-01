package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.Category;
import ar.gob.inti.argentinaprograma.miportfolio.repository.CategoryRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository _repository;

    @Override
    public List<Category> findAll() throws NotFoundException {
        List<Category> categories = _repository.findAll();

        if (categories.isEmpty()) {

            throw new NotFoundException("No se encontraron datos.");
        }

        return categories;
    }

    @Override
    public Category findById(UUID id) throws NotFoundException {
        return _repository.findById(id).orElseThrow(() -> new NotFoundException(Category.class, id));
    }

    @Override
    public Category create(Category category) {
        try{
            return _repository.save(category);
        }
        catch(IllegalArgumentException ex){
            throw new NotFoundException("Compruebe y vuelva a intentarlo");
        }
    }

    @Override
    public Category update(UUID id, Category category) throws NotFoundException {
        if (_repository.existsById(id)) {
            _repository.save(category);
        } else {
            throw new NotFoundException(Category.class, id);
        }
        return category;
    }

    @Override
    public void deleteById(UUID id) {
        _repository.deleteById(id);
    }
}