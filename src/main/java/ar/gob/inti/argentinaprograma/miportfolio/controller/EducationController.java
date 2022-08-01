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

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestEducation;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseEducation;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperEducation;
import ar.gob.inti.argentinaprograma.miportfolio.model.Category;
import ar.gob.inti.argentinaprograma.miportfolio.model.Education;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.service.ICategoryService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IEducationService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Education", description = "Education controller")
@RestController
@RequestMapping("/api")
public class EducationController {

    @Autowired
    private IEducationService _serviceEducation;

    @Autowired
    private IProfileService _serviceProfile;

    @Autowired
    private ICategoryService _serviceCategory;

    @Autowired
    private MapperEducation _mapper;

    @Operation(summary = "Get All", description = "Get all educations of a profile", tags = { "Education" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseEducation.class))))
    })

    @GetMapping("/profiles/{profile_id}/educations")
    public ResponseEntity<List<ResponseEducation>> getAll(@PathVariable(value = "profile_id") UUID profileId) {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceEducation.findAllByProfileId(profileId)));
    }

    @Operation(summary = "Create", description = "Create a new education for the profile", tags = { "Education" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEducation.class))),
            @ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PostMapping("/profiles/{profile_id}/educations")
    public ResponseEntity<?> create(@PathVariable(value = "profile_id") UUID profileId,
            @Valid @RequestBody RequestEducation request) {
        try {
            Profile profile = _serviceProfile.findById(profileId);
            Category category = _serviceCategory.findById(request.getCategory().getId());

            Education education = _mapper.toEntity(request);

            education.setCategory(category);
            education.setProfile(profile);

            return ResponseEntity.ok().body(_mapper.toResponse(_serviceEducation.create(education)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        }
    }

    @Operation(summary = "Get By Id", description = "Get a profile's education", tags = { "Education" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEducation.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @GetMapping("/educations/{education_id}")
    public ResponseEntity<?> getById(@PathVariable(value = "education_id") UUID id) {
        try {
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceEducation.findById(id)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Update", description = "Update a profile's education", tags = { "Education" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEducation.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PutMapping("/educations/{education_id}")
    public ResponseEntity<?> update(@PathVariable("education_id") UUID id,
            @Valid @RequestBody RequestEducation request) {
        try {
            Education entity = _serviceEducation.findById(id);
            Category category = _serviceCategory.findById(request.getCategory().getId());
            
            Education updated =_mapper.toEntity(entity, request);
            updated.setCategory(category);

            return ResponseEntity.ok().body(_mapper.toResponse(_serviceEducation.update(id, updated)));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Delete By Profile Id", description = "Delete a profile's education", tags = { "Education" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @DeleteMapping("/educations/{education_id}")
    public ResponseEntity<?> delete(@PathVariable("education_id") UUID id) {
        try {
            _serviceEducation.deleteById(id);
            return ResponseEntity.status(204).body(new ResponseMessage(""));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }
}