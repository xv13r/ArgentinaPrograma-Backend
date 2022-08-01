package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUser {
    
    @NotBlank(message = "Requerido")
    @Size(min = 6, message = "Debe tener al menos 6 caracteres")
    @Size(max = 15, message = "Debe tener como máximo 15 caracteres")
    private String username;

    @NotBlank(message = "Requerido")
    @Size(min = 3, message = "Debe tener al menos 5 caracteres")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "Requerido")
    @Size(min = 8, message = "Debe tener al menos 8 caracteres")
    private String password;
}
