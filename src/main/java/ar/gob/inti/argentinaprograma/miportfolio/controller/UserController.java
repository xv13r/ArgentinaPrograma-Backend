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

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestUser;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.UserResponse;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperUser;
import ar.gob.inti.argentinaprograma.miportfolio.model.User;
import ar.gob.inti.argentinaprograma.miportfolio.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User controller")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService _serviceUser;

     @Autowired
    private MapperUser _mapper;

    @Operation(summary = "Get All", description = "Get all users", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))))
    })

    @RolesAllowed({ RoleEnum.ROLE_ADMIN })
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceUser.findAll()));
    }

    @Operation(summary = "Create", description = "Create a new user", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PostMapping("/users")
    public ResponseEntity<?> create(@Valid @RequestBody RequestUser request) {
        try {
            User user = _mapper.toEntity(request);
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceUser.create(user)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        }
    }

    @Operation(summary = "Get By Id", description = "Get a user", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @GetMapping("/users/{user_id}")
    public ResponseEntity<?> getById(@PathVariable(value = "user_id") UUID id) {
        try {
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceUser.findById(id)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Update", description = "Update a user", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PutMapping("/users/{user_id}")
    public ResponseEntity<?> update(@PathVariable("user_id") UUID id, @Valid @RequestBody RequestUser request) {
        try {
            User entity = _mapper.toEntity(request);
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceUser.update(id, entity)));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Delete By Id", description = "Delete a user", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_ADMIN})
    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<?> delete(@PathVariable("user_id") UUID id) {
        try {
            _serviceUser.deleteById(id);
            return ResponseEntity.status(204).body(new ResponseMessage(""));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }
}