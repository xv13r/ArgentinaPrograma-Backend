package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class RequestFile {
    @NotBlank(message = "Requerido")
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
