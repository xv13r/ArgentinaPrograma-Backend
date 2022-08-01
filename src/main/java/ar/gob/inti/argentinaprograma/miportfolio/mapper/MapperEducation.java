package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestEducation;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseEducation;
import ar.gob.inti.argentinaprograma.miportfolio.model.Education;

@Component
public class MapperEducation {
    
    public List<ResponseEducation> toResponseList(List<Education> educations){
        List<ResponseEducation> educationsResponse = new ArrayList<>();
        
        if(educations != null)
        {
            educationsResponse = educations.stream().map(education -> { 
                return new ResponseEducation(
                    education.getId(), 
                    education.getSchool(),
                    education.getCareer(),
                    education.getStartDate(),
                    education.getEndDate(),
                    education.getImageId(),
                    education.getCategory()
                );
              }).collect(Collectors.toList());
        }
        return educationsResponse;
    }
    
    public Education toEntity(RequestEducation request){
        Education education = new Education();
        if (request != null){
                education.setSchool(request.getSchool());
                education.setCareer(request.getCareer());
                education.setStartDate(request.getStartDate());
                education.setEndDate(request.getEndDate());
                education.setImageId(request.getImageId());
                //education.setCategory(request.getCategory());
        }
        return education;
    }

    public Education toEntity(Education updated, RequestEducation request){
        if (request != null){
            if (request != null){
                updated.setSchool(request.getSchool());
                updated.setCareer(request.getCareer());
                updated.setStartDate(request.getStartDate());
                updated.setEndDate(request.getEndDate());
            }
        }
        return updated;
    }

    public ResponseEducation toResponse(Education education){
        return new ResponseEducation(
            education.getId(), 
            education.getSchool(),
            education.getCareer(),
            education.getStartDate(),
            education.getEndDate(),
            education.getImageId(),
            education.getCategory()
        );
    }
}