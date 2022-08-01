package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestExperience;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseExperience;
import ar.gob.inti.argentinaprograma.miportfolio.model.Experience;

@Component
public class MapperExperience {
    
    public List<ResponseExperience> toResponseList(List<Experience> experiences){
        List<ResponseExperience> experiencesResponse = new ArrayList<>();
        
        if(experiences != null)
        {
            experiencesResponse = experiences.stream().map(experience -> { 
                return new ResponseExperience(
                    experience.getId(), 
                    experience.getCompany(),
                    experience.getDescription(),
                    experience.getStartDate(),
                    experience.getEndDate(),
                    experience.getImage(),
                    experience.getEmployment()
                );
              }).collect(Collectors.toList());
        }
        return experiencesResponse;
    }

    public Experience toEntity(RequestExperience request){
        Experience experience = new Experience();
        if (request != null){
                experience.setCompany(request.getCompany());
                experience.setDescription(request.getDescription());
                experience.setStartDate(request.getStartDate());
                experience.setEndDate(request.getEndDate());
                // experience.setImage(request.getImage());
                // experience.setEmployment(request.getEmployment());
        }
        return experience;
    }

    public Experience toEntity(Experience updated, RequestExperience request){
        if (request != null){
            updated.setCompany(request.getCompany());
            updated.setDescription(request.getDescription());
            updated.setStartDate(request.getStartDate());
            updated.setEndDate(request.getEndDate());
        }
        return updated;
    }

    public ResponseExperience toResponse(Experience experience){
        return new ResponseExperience(
            experience.getId(), 
            experience.getCompany(),
            experience.getDescription(),
            experience.getStartDate(),
            experience.getEndDate(),
            experience.getImage(),
            experience.getEmployment()
        );
    }
}