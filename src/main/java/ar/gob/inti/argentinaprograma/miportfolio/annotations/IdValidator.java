package ar.gob.inti.argentinaprograma.miportfolio.annotations;

import java.util.UUID;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdValidator implements ConstraintValidator<IdValidation, UUID> {
    public static final String UUID_STRING = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    @Override
    public void initialize(IdValidation validUuid) { }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext cxt) {
        Pattern p = Pattern.compile(IdValidator.UUID_STRING);
        return p.matcher(uuid.toString()).matches();
        //return uuid.toString().matches(IdValidator.UUID_STRING);
    }
}