package ar.gob.inti.argentinaprograma.miportfolio.annotations;

import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ImageTypeValidator implements ConstraintValidator<ImageType, MultipartFile> {

    private ArrayList<String> contentType;

    @Override
    public void initialize(ImageType constraintAnnotation) {
        contentType = new ArrayList<String>();

        for (int i = 0; i < constraintAnnotation.type().length; i++) {
            contentType.add("image/" + constraintAnnotation.type()[i].toLowerCase());
        }
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        boolean result = true;

        if (multipartFile == null || multipartFile.isEmpty() || multipartFile.getSize() == 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    context.getDefaultConstraintMessageTemplate())
                    .addConstraintViolation();
            return false;
        }

        String contentType = multipartFile.getContentType();

        if (!isSupportedContentType(contentType)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
            context.getDefaultConstraintMessageTemplate())
            .addConstraintViolation();

            result = false;
        }

        return result;
    }

    private boolean isSupportedContentType(String type) {
        return this.contentType.contains(type);
    }
}