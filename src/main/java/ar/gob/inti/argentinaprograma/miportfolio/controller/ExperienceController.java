// package ar.gob.inti.argentinaprograma.miportfolio.controller;

// import java.util.List;
// import java.util.UUID;

// import javax.annotation.security.RolesAllowed;
// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

// import ar.gob.inti.argentinaprograma.miportfolio.dto.request.ExperienceRequest;
// import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ExperienceResponse;
// import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
// import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
// import ar.gob.inti.argentinaprograma.miportfolio.mapper.ExperienceMapper;
// import ar.gob.inti.argentinaprograma.miportfolio.message.ResponseMessage;
// import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;
// import ar.gob.inti.argentinaprograma.miportfolio.model.Experience;
// import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
// import ar.gob.inti.argentinaprograma.miportfolio.service.IEmploymentService;
// import ar.gob.inti.argentinaprograma.miportfolio.service.IExperienceService;
// import ar.gob.inti.argentinaprograma.miportfolio.service.IImageService;
// import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.ArraySchema;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @Tag(name = "Experience", description = "Experience controller")
// @RestController
// @RequestMapping("/api")
// public class ExperienceController {

//         @Autowired
//         private IExperienceService _serviceExperience;

//         @Autowired
//         private IEmploymentService _serviceEmployment;

//         @Autowired
//         private IImageService _serviceImage;

//         @Autowired
//         private IProfileService _serviceProfile;

//         @Autowired
//         private ExperienceMapper _mapper;

//         @Operation(summary = "Get All", description = "Get all experience of a profile", tags = { "Experience" })
//         @ApiResponses(value = {
//                         @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExperienceResponse.class)))),
//                         @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {
//                                         String.class })))
//         })

//         @GetMapping("/profiles/{profile_id}/experiences")
//         public ResponseEntity<List<ExperienceResponse>> getAll(@PathVariable(value = "profile_id") UUID profileId) {
//                 return new ResponseEntity<>(_mapper.toResponseList(_serviceExperience.findAllByProfileId(profileId)),
//                                 HttpStatus.OK);
//         }

//         @Operation(summary = "Create", description = "Create a new experience for the profile", tags = { "Experience" })
//         @ApiResponses(value = {
//                         @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExperienceResponse.class)))),
//                         @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {
//                                         String.class })))
//         })

//         @RolesAllowed({ RoleEnum.ROLE_USER })
//         @PostMapping("/profiles/{profile_id}/experiences")
//         public ResponseEntity<ExperienceResponse> create(@PathVariable(value = "profile_id") UUID profileId,
//                         @Valid @ModelAttribute ExperienceRequest experienceRequest) {
//                 Profile profile = _serviceProfile.findById(profileId);
//                 Employment employment = _serviceEmployment.findById(experienceRequest.getEmployment());
//                 Experience experience = _mapper.toEntity(experienceRequest);
//                 String filename = _serviceImage.save(experienceRequest.getImage());
//                 experience.setImage(filename);
//                 experience.setEmployment(employment);
//                 experience.setPerson(profile);
//                 return new ResponseEntity<>(_mapper.toResponse(_serviceExperience.create(experience)),
//                                 HttpStatus.CREATED);
//         }

//         @Operation(summary = "Get By Id", description = "Get a profile's experience", tags = { "Experience" })
//         @ApiResponses(value = {
//                         @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExperienceResponse.class)))),
//                         @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NotFoundException.class))))
//                 })


//         @RolesAllowed({ RoleEnum.ROLE_USER })
//         @GetMapping("/experiences/{experience_id}")
//         public ResponseEntity<?> getById(@PathVariable(value = "experience_id") UUID id) {
//                 try{
//                         return ResponseEntity.ok().body(_mapper.toResponse(_serviceExperience.findById(id)));
                    
//                 } catch (NotFoundException ex) {
//                     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
//                 }
//         }

//         @Operation(summary = "Update", description = "Update a profile's experience", tags = { "Experience" })
//         @ApiResponses(value = {
//                         @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExperienceResponse.class)))),
//                         @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NotFoundException.class))))
//                 })

//         @RolesAllowed({ RoleEnum.ROLE_USER })
//         @PutMapping("/experiences/{experience_id}")
//         public ResponseEntity<?> update(@PathVariable("experience_id") UUID id,  @Valid @ModelAttribute ExperienceRequest request) {
//                 try {
//                         Experience entity = _serviceExperience.findById(id);
//                         Employment employment = _serviceEmployment.findById(request.getEmployment());

//                         entity = _mapper.toEntity(request);
//                         entity.setEmployment(employment);

//                         return ResponseEntity.ok().body(_mapper.toResponse(_serviceExperience.update(id, entity)));

//                 } catch (NotFoundException ex) {
//                         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
//                 }
//         }

//         @Operation(summary = "Delete By Profile Id", description = "Delete a profile's experience", tags = {
//                         "Experience" })
//         @ApiResponses(value = {
//                         @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
//                         @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NotFoundException.class))))
//         })

//         @RolesAllowed({ RoleEnum.ROLE_USER })
//         @DeleteMapping("/experiences/{experience_id}")
//         public ResponseEntity<?> delete(@PathVariable("experience_id") UUID id) {
//                 try {
//                         _serviceExperience.deleteById(id);
//                         return ResponseEntity.status(204).build();

//                 } catch (NotFoundException ex) {
//                         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
//                 }
//         }
// }
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

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestExperience;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseExperience;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperExperience;
import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;
import ar.gob.inti.argentinaprograma.miportfolio.model.Experience;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.service.IEmploymentService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IExperienceService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Experience", description = "Experience controller")
@RestController
@RequestMapping("/api")
public class ExperienceController {

    @Autowired
    private IExperienceService _serviceExperience;

    @Autowired
    private IProfileService _serviceProfile;

    @Autowired
    private IEmploymentService _serviceEmployment;

    @Autowired
    private MapperExperience _mapper;

    @Operation(summary = "Get All", description = "Get all experiences of a profile", tags = { "Experience" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseExperience.class))))
    })

    @GetMapping("/profiles/{profile_id}/experiences")
    public ResponseEntity<List<ResponseExperience>> getAll(@PathVariable(value = "profile_id") UUID profileId) {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceExperience.findAllByProfileId(profileId)));
    }

    @Operation(summary = "Create", description = "Create a new experience for the profile", tags = { "Experience" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseExperience.class))),
            @ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PostMapping("/profiles/{profile_id}/experiences")
    public ResponseEntity<?> create(@PathVariable(value = "profile_id") UUID profileId,
             @RequestBody RequestExperience request) {
        try {
            Profile profile = _serviceProfile.findById(profileId);
            Employment employment = _serviceEmployment.findById(request.getEmployment().getId());

            Experience experience = _mapper.toEntity(request);

            experience.setEmployment(employment);
            experience.setProfile(profile);

            return ResponseEntity.ok().body(_mapper.toResponse(_serviceExperience.create(experience)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        }
    }

    @Operation(summary = "Get By Id", description = "Get a profile's experience", tags = { "Experience" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseExperience.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @GetMapping("/experiences/{experience_id}")
    public ResponseEntity<?> getById(@PathVariable(value = "experience_id") UUID id) {
        try {
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceExperience.findById(id)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Update", description = "Update a profile's experience", tags = { "Experience" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseExperience.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PutMapping("/experiences/{experience_id}")
    public ResponseEntity<?> update(@PathVariable("experience_id") UUID id, @Valid @RequestBody RequestExperience request) {
        try {
            Experience entity = _serviceExperience.findById(id);
            Employment employment = _serviceEmployment.findById(request.getEmployment().getId());
            
            Experience updated =_mapper.toEntity(entity, request);
            updated.setEmployment(employment);

            return ResponseEntity.ok().body(_mapper.toResponse(_serviceExperience.update(id, updated)));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Delete By Profile Id", description = "Delete a profile's experience", tags = { "Experience" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @DeleteMapping("/experiences/{experience_id}")
    public ResponseEntity<?> delete(@PathVariable("experience_id") UUID id) {
        try {
            _serviceExperience.deleteById(id);
            return ResponseEntity.status(204).body(new ResponseMessage(""));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }
}