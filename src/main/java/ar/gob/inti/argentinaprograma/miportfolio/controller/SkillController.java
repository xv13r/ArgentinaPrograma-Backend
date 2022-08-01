package ar.gob.inti.argentinaprograma.miportfolio.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestSkill;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.SkillResponse;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperSkill;
import ar.gob.inti.argentinaprograma.miportfolio.model.Skill;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.service.ISkillService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Skill", description = "Skill controller")
@RestController
@RequestMapping("/api")
public class SkillController {

    @Autowired
    private ISkillService _serviceSkill;

    @Autowired
    private IProfileService _serviceProfile;

    @Autowired
    private MapperSkill _mapper;

    @Operation(summary = "Get All", description = "Get all skills of a profile", tags = { "Skill" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SkillResponse.class))))
    })

    @GetMapping("/profiles/{profile_id}/skills")
    public ResponseEntity<List<SkillResponse>> getAll(@PathVariable(value = "profile_id") UUID profileId) {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceSkill.findAllByProfileId(profileId)));
    }

    @Operation(summary = "Create", description = "Create a new skill for the profile", tags = { "Skill" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillResponse.class))),
            @ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PostMapping("/profiles/{profile_id}/skills")
    public ResponseEntity<?> create(@PathVariable(value = "profile_id") UUID profileId,
            @Valid @RequestBody RequestSkill skillRequest) {
        try {
            Profile profile = _serviceProfile.findById(profileId);
            Skill skill = _mapper.toEntity(skillRequest);
            skill.setProfile(profile);
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceSkill.create(skill)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        }
    }

    @Operation(summary = "Get By Id", description = "Get a profile's skill", tags = { "Skill" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @GetMapping("/skills/{skill_id}")
    public ResponseEntity<?> getById(@PathVariable(value = "skill_id") UUID id) {
        try {
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceSkill.findById(id)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Update", description = "Update a profile's skill", tags = { "Skill" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PutMapping("/skills/{skill_id}")
    public ResponseEntity<?> update(@PathVariable("skill_id") UUID id, @Valid @RequestBody RequestSkill request) {
        try {
            Skill entity = _serviceSkill.findById(id);
            Skill updated =_mapper.toEntity(entity, request);

            return ResponseEntity.ok().body(_mapper.toResponse(_serviceSkill.update(id, updated)));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Delete By Profile Id", description = "Delete a profile's skill", tags = { "Skill" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @DeleteMapping("/skills/{skill_id}")
    public ResponseEntity<?> delete(@PathVariable("skill_id") UUID id) {
        try {
            _serviceSkill.deleteById(id);
            return ResponseEntity.status(204).body(new ResponseMessage(""));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }
}