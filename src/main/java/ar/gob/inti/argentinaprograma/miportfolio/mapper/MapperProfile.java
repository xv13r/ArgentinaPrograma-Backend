package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestProfile;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseProfile;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;

@Component
public class MapperProfile {
    
    public List<ResponseProfile> toResponseList(List<Profile> profiles){
        List<ResponseProfile> profilesResponse = new ArrayList<>();
        
        if(profiles != null)
        {
            profilesResponse = profiles.stream().map(profile -> { 
                return new ResponseProfile(
                    profile.getId(), 
                    profile.getName(), 
                    profile.getLastname(), 
                    profile.getBirthday(), 
                    profile.getTitle(), 
                    profile.getAbout(),
                    profile.getAvatarId(),
                    profile.getBannerId(),
                    profile.getUser().getId()
                ); 
            }).collect(Collectors.toList());
        }
        return profilesResponse;
    }
    
    public Profile toEntity(RequestProfile request){
        Profile profile = new Profile();
        if (request != null){
            profile.setName(request.getName());
            profile.setLastname(request.getLastname());
            profile.setBirthday(request.getBirthday());
            profile.setTitle(request.getTitle());
            profile.setAbout(request.getAbout());
            profile.setBannerId(request.getBannerId());
            profile.setAvatarId(request.getAvatarId());
        }
        return profile;
    }

    public Profile toEntity(Profile updated, RequestProfile request){
        if (request != null){
            updated.setName(request.getName());
            updated.setLastname(request.getLastname());
            updated.setBirthday(request.getBirthday());
            updated.setTitle(request.getTitle());
            updated.setAbout(request.getAbout());
            updated.setBannerId(request.getBannerId());
            updated.setAvatarId(request.getAvatarId());
        }
        return updated;
    }

    public ResponseProfile toResponse(Profile profile){
        return new ResponseProfile(
            profile.getId(), 
            profile.getName(), 
            profile.getLastname(), 
            profile.getBirthday(), 
            profile.getTitle(), 
            profile.getAbout(),
            profile.getAvatarId(),
            profile.getBannerId(),
            profile.getUser().getId()
        );
    }
}