package ar.gob.inti.argentinaprograma.miportfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseEmployment;
import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperEmployment;
import ar.gob.inti.argentinaprograma.miportfolio.service.IEmploymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Employment", description = "Employment controller")
@RestController
@RequestMapping("/api")
public class EmploymentController {

    @Autowired
    private IEmploymentService _serviceEmployment;

    @Autowired
    private MapperEmployment _mapper;

    @Operation(summary = "Get All", description = "Get all employments", tags = { "Employment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseEmployment.class))))
    })

    @GetMapping("/employments")
    public ResponseEntity<List<ResponseEmployment>> getAll() {
        return ResponseEntity.ok().body(_mapper.toResponseList(_serviceEmployment.findAll()));
    }
}