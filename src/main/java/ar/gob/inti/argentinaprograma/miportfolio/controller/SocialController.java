package ar.gob.inti.argentinaprograma.miportfolio.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestSocial;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseCategory;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseSocial;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperCategory;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperSocial;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.model.Social;
import ar.gob.inti.argentinaprograma.miportfolio.service.ICategoryService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;
import ar.gob.inti.argentinaprograma.miportfolio.service.ISocialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Social", description = "Social controller")
@RestController
@RequestMapping("/api")
public class SocialController {

    @Autowired
    private IProfileService _serviceProfile;

    @Autowired
    private ISocialService _serviceSocial;

    @Autowired
    private MapperSocial _mapper;

    @Operation(summary = "Get All", description = "Get all socials of a profile", tags = { "Education" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseSocial.class))))
    })

    @GetMapping("/profiles/{profile_id}/socials")
    public ResponseEntity<List<ResponseSocial>> getAll(@PathVariable(value = "profile_id") UUID profileId) {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceSocial.findAllByProfileId(profileId)));
    }

     @Operation(summary = "Create", description = "Create a new social for the profile", tags = { "Education" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSocial.class))),
            @ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PostMapping("/profiles/{profile_id}/socials")
    public ResponseEntity<?> create(@PathVariable(value = "profile_id") UUID profileId,
            @Valid @RequestBody RequestSocial request) {
        try {
            Profile profile = _serviceProfile.findById(profileId);

            Social social = _mapper.toEntity(request);
            social.setProfile(profile);

            return ResponseEntity.ok().body(_mapper.toResponse(_serviceSocial.create(social)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        }
    }
}
