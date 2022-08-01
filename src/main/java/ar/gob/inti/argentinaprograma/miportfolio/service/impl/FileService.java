package ar.gob.inti.argentinaprograma.miportfolio.service.impl;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
import ar.gob.inti.argentinaprograma.miportfolio.model.File;
import ar.gob.inti.argentinaprograma.miportfolio.repository.FileRepository;
import ar.gob.inti.argentinaprograma.miportfolio.service.IFileService;

@Service
public class FileService implements IFileService {

    @Autowired
    private FileRepository _fileRepository;

    public File create(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File entity = new File(fileName, file.getContentType(), file.getBytes());
        return _fileRepository.save(entity);
    }

    public File findById(UUID id) {
        File file = _fileRepository.findById(id).get();
        if (file !=null){
        return file;
        }
        else{
            throw new NotFoundException(File.class, id);
        }
    }

    public Stream<File> findAll() {
        return _fileRepository.findAll().stream();
    }
}