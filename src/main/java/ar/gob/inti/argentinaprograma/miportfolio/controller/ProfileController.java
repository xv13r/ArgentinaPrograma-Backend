package ar.gob.inti.argentinaprograma.miportfolio.controller;

import java.io.Console;
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

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestFile;
import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestProfile;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseProfile;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperProfile;
import ar.gob.inti.argentinaprograma.miportfolio.model.File;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.model.User;
import ar.gob.inti.argentinaprograma.miportfolio.service.IFileService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Profile", description = "Profile controller")
@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private IProfileService _serviceProfile;

    @Autowired
    private IUserService _serviceUser;

    @Autowired
    private MapperProfile _mapper;

    @Operation(summary = "Get All", description = "Get all profiles", tags = { "Profile" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseProfile.class))))
    })

    @RolesAllowed({ RoleEnum.ROLE_ADMIN })
    @GetMapping("/profiles")
    public ResponseEntity<List<ResponseProfile>> getAll() {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceProfile.findAll()));
    }

    @Operation(summary = "Create", description = "Create a new profile", tags = { "Profile" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseProfile.class))),
            @ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PostMapping("/profiles")
    public ResponseEntity<?> create(@Valid @RequestBody RequestProfile request) {
        try {
            User user = _serviceUser.findById(request.getUserId());
            Profile profile = _mapper.toEntity(request);
            profile.setUser(user);

            return ResponseEntity.ok().body(_mapper.toResponse(_serviceProfile.create(profile)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        }
    }

    @Operation(summary = "Get By Id", description = "Get a profile", tags = { "Profile" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseProfile.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @GetMapping("/profiles/{profile_id}")
    public ResponseEntity<?> getById(@PathVariable(value = "profile_id") UUID id) {
        try {
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceProfile.findById(id)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Get By Id", description = "Get a profile", tags = { "Profile" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseProfile.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @GetMapping("/profiles/{user_id}/user")
    public ResponseEntity<?> getByUserId(@PathVariable(value = "user_id") UUID id) {
        try {
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceProfile.findByUserId(id)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Update", description = "Update a profile", tags = { "Profile" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseProfile.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PutMapping("/profiles/{profile_id}")
    public ResponseEntity<?> update(@PathVariable("profile_id") UUID id, @Valid @RequestBody RequestProfile request) {
        try {
            Profile profile = _serviceProfile.findById(id);
            Profile updated =_mapper.toEntity(profile, request);
            
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceProfile.update(updated)));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Delete By Profile Id", description = "Delete a profile", tags = { "Profile" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_ADMIN })
    @DeleteMapping("/profiles/{profile_id}")
    public ResponseEntity<?> delete(@PathVariable("profile_id") UUID id) {
        try {
            _serviceProfile.deleteById(id);
            return ResponseEntity.status(204).body(new ResponseMessage(""));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }
}