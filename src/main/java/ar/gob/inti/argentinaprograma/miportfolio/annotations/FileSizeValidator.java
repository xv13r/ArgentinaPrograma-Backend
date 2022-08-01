package ar.gob.inti.argentinaprograma.miportfolio.annotations;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private static final Integer MB=1024*1024;

    private long maxSizeInMB;

    @Override
    public void initialize(FileSize fileSize) {
        this.maxSizeInMB=fileSize.maxSizeInMB();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, 
		ConstraintValidatorContext constraintValidatorContext) {

        if(multipartFile==null)
            return true;

        return multipartFile.getSize()<maxSizeInMB*MB;
    }
}