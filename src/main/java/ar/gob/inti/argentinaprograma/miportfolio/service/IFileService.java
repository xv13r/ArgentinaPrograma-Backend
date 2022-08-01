package ar.gob.inti.argentinaprograma.miportfolio.service;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import ar.gob.inti.argentinaprograma.miportfolio.model.File;

public interface IFileService {
    public File create(MultipartFile file) throws IOException;
    public File findById(UUID id);
    public Stream<File> findAll();
}