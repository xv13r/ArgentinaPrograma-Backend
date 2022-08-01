package ar.gob.inti.argentinaprograma.miportfolio.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseFile;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseMessage;
import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.mapper.MapperFile;
import ar.gob.inti.argentinaprograma.miportfolio.model.File;
import ar.gob.inti.argentinaprograma.miportfolio.service.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Upload", description = "Upload controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FileController {

  @Autowired
  private IFileService _fileStorage;

  @Autowired
  private MapperFile _mapper;

  // @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces
  // = MediaType.MULTIPART_FORM_DATA_VALUE)
  // @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
  // // @PostMapping("/upload", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
  // public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile
  // file) {
  // String message = "";
  // try {
  // String filename = _uploadStorage.save(file);

  // message = "Uploaded the file successfully: " + filename;
  // return ResponseEntity.status(HttpStatus.OK).body(message);
  // } catch (Exception e) {
  // message = "Could not upload the file: " + file.getOriginalFilename() + "!";
  // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Fail");
  // }
  // }

  // @PostMapping("/upload")
  // public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files")
  // MultipartFile[] files) {
  // String message = "";
  // try {
  // List<String> fileNames = new ArrayList<>();
  // Arrays.asList(files).stream().forEach(file -> {
  // storageService.save(file);
  // fileNames.add(file.getOriginalFilename());
  // });
  // message = "Uploaded the files successfully: " + fileNames;
  // return ResponseEntity.status(HttpStatus.OK).body(new
  // ResponseMessage(message));
  // } catch (Exception e) {
  // message = "Fail to upload files!";
  // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
  // ResponseMessage(message));
  // }
  // }

  // @GetMapping("/files")
  // public ResponseEntity<List<String>> getListFiles() {
  // List<String> fileInfos = _uploadStorage.findAll().map(path -> {
  // String url = MvcUriComponentsBuilder
  // .fromMethodName(FileController.class, "get",
  // path.getFileName().toString()).build().toString();
  // return url;
  // }).collect(Collectors.toList());
  // return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  // }

  // @GetMapping("/files/{filename:.+}")
  // @ResponseBody
  // public ResponseEntity<Resource> get(@PathVariable String filename) {
  // Resource file = _uploadStorage.findByName(filename);

  // return ResponseEntity.ok()
  // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
  // file.getFilename() + "\"").body(file);
  // }

  @PostMapping("/upload")
  public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
    try {
      File fileUpload = _fileStorage.create(file);
      return ResponseEntity.status(HttpStatus.OK).body(fileUpload.getId());
    }
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseMessage("Could not upload the file: " + file.getOriginalFilename()));
    }
  }

  @Operation(summary = "Get file", description = "Get a file by id", tags = { "File" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "image/*", array = @ArraySchema(schema = @Schema(implementation = byte.class)))),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
  })

  @RolesAllowed({ RoleEnum.ROLE_USER })
  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getById(@PathVariable UUID id) {
    File file = _fileStorage.findById(id);

    ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(file.getName(), StandardCharsets.UTF_8)
                .build();
                
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
        .contentType(MediaType.valueOf(file.getType()))
        .body(file.getData());
        // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
        // .body(file.getData());
  }

  @Operation(summary = "Get all files", description = "Get all files by id", tags = { "File" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "image/*", array = @ArraySchema(schema = @Schema(implementation = ResponseFile.class)))),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)))
  })

  @RolesAllowed({ RoleEnum.ROLE_USER })
  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getAll() {
    List<ResponseFile> files = _fileStorage.findAll().map(file -> {
      String fileUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/files/")
          .path(file.getId().toString())
          .toUriString();

      return new ResponseFile(
          file.getId(),
          file.getName(),
          file.getType(),
          file.getData());
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
  }
}