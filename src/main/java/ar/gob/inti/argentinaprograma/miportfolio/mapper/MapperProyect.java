package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestProyect;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseProyect;
import ar.gob.inti.argentinaprograma.miportfolio.model.Proyect;

@Component
public class MapperProyect {
    
    public List<ResponseProyect> toResponseList(List<Proyect> proyects){
        List<ResponseProyect> proyectsResponse = new ArrayList<>();
        
        if(proyects != null)
        {
            proyectsResponse = proyects.stream().map(proyect -> { 
                return new ResponseProyect(
                    proyect.getId(), 
                    proyect.getName(),
                    proyect.getDescription(),
                    proyect.getLink(),
                    proyect.getCreated(),
                    proyect.getImages()
                );
              }).collect(Collectors.toList());
        }
        return proyectsResponse;
    }

    public Proyect toEntity(RequestProyect request){
        Proyect proyect = new Proyect();
        if (request != null){
                proyect.setName(request.getName());
                proyect.setDescription(request.getDescription());
                proyect.setLink(request.getLink());
                proyect.setCreated(request.getCreated());
                // proyect.setImages(request.getImages());
        }
        return proyect;
    }

    public Proyect toEntity(Proyect updated, RequestProyect request){
        if (request != null){
            updated.setName(request.getName());
            updated.setDescription(request.getDescription());
            updated.setLink(request.getLink());
            updated.setCreated(request.getCreated());
        }
        return updated;
    }

    public ResponseProyect toResponse(Proyect proyect){
        return new ResponseProyect(
            proyect.getId(), 
            proyect.getName(),
            proyect.getDescription(),
            proyect.getLink(),
            proyect.getCreated(),
            proyect.getImages()
        );
    }
}