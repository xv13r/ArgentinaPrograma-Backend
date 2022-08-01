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

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestAddress;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseAddress;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.exception.ValidationException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperAddress;
import ar.gob.inti.argentinaprograma.miportfolio.model.Address;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.service.IAddressService;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Address", description = "Address controller")
@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private IAddressService _serviceAddress;

    @Autowired
    private IProfileService _serviceProfile;

    @Autowired
    private MapperAddress _mapper;

    @Operation(summary = "Get All", description = "Get all addresses of a profile", tags = { "Address" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseAddress.class))))
    })

    @GetMapping("/profiles/{profile_id}/addresses")
    public ResponseEntity<List<ResponseAddress>> getAll(@PathVariable(value = "profile_id") UUID profileId) {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceAddress.findAllByProfileId(profileId)));
    }

    @Operation(summary = "Create", description = "Create a new address for the profile", tags = { "Address" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseAddress.class))),
            @ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PostMapping("/profiles/{profile_id}/addresses")
    public ResponseEntity<?> create(@PathVariable(value = "profile_id") UUID profileId,
            @Valid @RequestBody RequestAddress addressRequest) {
        try {
            Profile profile = _serviceProfile.findById(profileId);
            Address address = _mapper.toEntity(addressRequest);
            address.setProfile(profile);
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceAddress.create(address)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));
        }
    }

    @Operation(summary = "Get By Id", description = "Get a profile's address", tags = { "Address" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseAddress.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @GetMapping("/addresses/{address_id}")
    public ResponseEntity<?> getById(@PathVariable(value = "address_id") UUID id) {
        try {
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceAddress.findById(id)));
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error de validación"));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Update", description = "Update a profile's address", tags = { "Address" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseAddress.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @PutMapping("/addresses/{address_id}")
    public ResponseEntity<?> update(@PathVariable("address_id") UUID id, @Valid @RequestBody RequestAddress request) {
        try {
            Address entity = _mapper.toEntity(request);
            return ResponseEntity.ok().body(_mapper.toResponse(_serviceAddress.update(id, entity)));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }

    @Operation(summary = "Delete By Profile Id", description = "Delete a profile's address", tags = { "Address" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @RolesAllowed({ RoleEnum.ROLE_USER })
    @DeleteMapping("/addresses/{address_id}")
    public ResponseEntity<?> delete(@PathVariable("address_id") UUID id) {
        try {
            _serviceAddress.deleteById(id);
            return ResponseEntity.status(204).body(new ResponseMessage(""));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No encontrado"));
        }
    }
}