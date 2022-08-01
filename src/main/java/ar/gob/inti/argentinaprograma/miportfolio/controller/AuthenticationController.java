package ar.gob.inti.argentinaprograma.miportfolio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestAuth;
import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestRegister;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseAuth;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.model.User;
import ar.gob.inti.argentinaprograma.miportfolio.security.TokenUtil;
import ar.gob.inti.argentinaprograma.miportfolio.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Authentication controller")
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager _authenticationManager;

    @Autowired
    private TokenUtil _token;

    @Autowired
    private UserService _userService;

    @Operation(summary = "Login", description = "Log in with email and password", tags = { "Authentication" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseAuth.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid RequestAuth request) {
        try {
            Authentication authentication = _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));

            User user = (User) authentication.getPrincipal();
            String accessToken = _token.generateAccessToken(user);
            ResponseAuth response = new ResponseAuth(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("No autorizado"));
        }
    }

    @Operation(summary = "Register", description = "Register a new user", tags = { "Authentication" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =  @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
    })

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody @Valid RequestRegister request) {

        if (_userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Correo electrónico ya está en uso"));
        }

        User user = new User();
        user.setName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        _userService.create(user);

        return ResponseEntity.ok().body(new ResponseMessage("Usuario registrado exitosamente"));
    }
}