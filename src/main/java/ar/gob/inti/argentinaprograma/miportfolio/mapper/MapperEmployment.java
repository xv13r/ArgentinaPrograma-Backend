package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseEmployment;
import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;

@Component
public class MapperEmployment {
    
    public List<ResponseEmployment> toResponseList(List<Employment> employments){
        List<ResponseEmployment> employmentsResponse = new ArrayList<>();
        
        if(employments != null)
        {
            employmentsResponse = employments.stream().map(employment -> { 
                return new ResponseEmployment(
                    employment.getId(), 
                    employment.getName()
                ); 
            }).collect(Collectors.toList());
        }
        return employmentsResponse;
    }

    public ResponseEmployment toResponse(Employment employment){
        return new ResponseEmployment(
            employment.getId(),
            employment.getName()
        );
    }
}