package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestSocial;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseSocial;
import ar.gob.inti.argentinaprograma.miportfolio.model.Social;

@Component
public class MapperSocial {
    
    public List<ResponseSocial> toResponseList(List<Social> socials){
        List<ResponseSocial> socialsResponse = new ArrayList<>();
        
        if(socials != null)
        {
            socialsResponse = socials.stream().map(entity -> { 
                return new ResponseSocial(
                    entity.getId(), 
                    entity.getName(),
                    entity.getUrl()
                ); 
            }).collect(Collectors.toList());
        }
        return socialsResponse;
    }

    public Social toEntity(RequestSocial request){
        Social social = new Social();
        if (request != null){
            social.setName(request.getName());
            social.setUrl(request.getUrl());
        }
        return social;
    }

    public Social toEntity(Social updated, RequestSocial request){
        if (request != null){
            updated.setName(request.getName());
            updated.setUrl(request.getUrl());
        }
        return updated;
    }

    public ResponseSocial toResponse(Social entity){
        return new ResponseSocial(
            entity.getId(),
            entity.getName(),
            entity.getUrl()
        );
    }
}