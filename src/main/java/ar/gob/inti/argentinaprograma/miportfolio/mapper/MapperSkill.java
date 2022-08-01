package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestSkill;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.SkillResponse;
import ar.gob.inti.argentinaprograma.miportfolio.model.Skill;

@Component
public class MapperSkill {
    
    public List<SkillResponse> toResponseList(List<Skill> skills){
        List<SkillResponse> skillsResponse = new ArrayList<>();
        
        if(skills != null)
        {
            skillsResponse = skills.stream().map(skill -> { 
                return new SkillResponse(
                    skill.getId(), 
                    skill.getName(), 
                    skill.getDescription(), 
                    skill.getProgress()
                ); 
            }).collect(Collectors.toList());
        }
        return skillsResponse;
    }
    
    public Skill toEntity(RequestSkill request){
        Skill skill = new Skill();
        if (request != null){
                skill.setName(request.getName());
                skill.setDescription(request.getDescription());
                skill.setProgress(request.getProgress());
         }
        return skill;
    }

    public Skill toEntity(Skill updated, RequestSkill request){
        if (request != null){
            updated.setName(request.getName());
            updated.setDescription(request.getDescription());
            updated.setProgress(request.getProgress());
        }
        return updated;
    }

    public SkillResponse toResponse(Skill skill){
        return new SkillResponse(
            skill.getId(), 
            skill.getName(), 
            skill.getDescription(), 
            skill.getProgress()
        );
    }
}