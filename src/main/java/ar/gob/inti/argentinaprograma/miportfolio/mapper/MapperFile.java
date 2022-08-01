package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseFile;
import ar.gob.inti.argentinaprograma.miportfolio.model.File;

@Component
public class MapperFile {
    
    public List<ResponseFile> toResponseList(List<File> entities){
        List<ResponseFile> response = new ArrayList<>();
        
        if(entities != null)
        {
            response = entities.stream().map(e -> { 
                return new ResponseFile(
                    e.getId(), 
                    e.getName(),
                    e.getType(),
                    e.getData()
                ); 
            }).collect(Collectors.toList());
        }
        return response;
    }
    public ResponseFile toResponse(File entity){
        return new ResponseFile(
            entity.getId(),
            entity.getName(),
            entity.getType(),
            entity.getData()
        );
    }
}