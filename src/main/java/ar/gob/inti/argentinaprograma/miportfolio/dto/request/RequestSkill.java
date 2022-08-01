package ar.gob.inti.argentinaprograma.miportfolio.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestSkill {

    @NotBlank(message = "Requerido")
    private String name;

    @NotBlank(message = "Requerido")
    private String description;

    @NotNull(message = "Requerido")
    @Min(value = 1, message = "Valor mínimo debe ser 1")
    @Max(value = 100, message = "Valor máximo debe ser 100")
    private Integer progress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}