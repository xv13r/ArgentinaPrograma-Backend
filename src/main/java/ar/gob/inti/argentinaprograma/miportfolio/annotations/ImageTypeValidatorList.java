package ar.gob.inti.argentinaprograma.miportfolio.annotations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ImageTypeValidatorList implements ConstraintValidator<ImageTypeList, List<MultipartFile>> {
    private ArrayList<String> contentType;

    @Override
    public void initialize(ImageTypeList constraintAnnotation) {
        contentType = new ArrayList<String>();

        for (int i = 0; i < constraintAnnotation.type().length; i++) {
            contentType.add("image/" + constraintAnnotation.type()[i].toLowerCase());
        }
    }

    @Override
    public boolean isValid(List<MultipartFile> listMultipartFile, ConstraintValidatorContext context) {

        boolean result = true;
        for (var multipartFile : listMultipartFile) {

            String contentType = multipartFile.getContentType();

            if (!isSupportedContentType(contentType)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                context.getDefaultConstraintMessageTemplate())
                .addConstraintViolation();

                result = false;
            }
        }
        return result;
    }

    private boolean isSupportedContentType(String type) {
        return this.contentType.contains(type);
    }
}