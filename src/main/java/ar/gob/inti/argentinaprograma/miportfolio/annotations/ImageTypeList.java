package ar.gob.inti.argentinaprograma.miportfolio.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageTypeValidatorList.class)
public @interface ImageTypeList {
    public String[] type() default {"jpeg", "jpg", "png"};

    public String message() default "Archivo de imagen no válido";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}