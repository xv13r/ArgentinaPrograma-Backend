package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestAuth {
    @NotBlank(message = "Requerido")
    @Size(min = 3, message = "Debe tener al menos 5 caracteres")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "Requerido")
    @Size(min = 8, message = "Debe tener al menos 8 caracteres")
    private String password;

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