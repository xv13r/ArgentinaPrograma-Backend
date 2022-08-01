package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestRegister {
    
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

    public RequestRegister(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}