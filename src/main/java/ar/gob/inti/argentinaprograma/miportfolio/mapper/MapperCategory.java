package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseCategory;
import ar.gob.inti.argentinaprograma.miportfolio.model.Category;

@Component
public class MapperCategory {
    
    public List<ResponseCategory> toResponseList(List<Category> categories){
        List<ResponseCategory> categoriesResponse = new ArrayList<>();
        
        if(categories != null)
        {
            categoriesResponse = categories.stream().map(category -> { 
                return new ResponseCategory(
                    category.getId(), 
                    category.getName()
                ); 
            }).collect(Collectors.toList());
        }
        return categoriesResponse;
    }

    public ResponseCategory toResponse(Category category){
        return new ResponseCategory(
            category.getId(),
            category.getName()
        );
    }
}