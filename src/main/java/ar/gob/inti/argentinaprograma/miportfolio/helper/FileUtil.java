// package ar.gob.inti.argentinaprograma.miportfolio.helper;

// import java.io.IOException;
// import java.io.InputStream;
// import java.nio.file.DirectoryStream;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.Iterator;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;
// import org.springframework.web.multipart.MultipartFile;

// import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;

// @Component
// public class FileUtil {

//     private static final String PROFILE_DIR = "profile";
    
//     @Value("${user.files.base.path}")
//     private String userFilesBasePath;

//     @Value("${max.file.upload.size}")
//     private Long maxFileUploadSize;
    
    
//     public Path fetchProfilePhotoByUserId(UUID profileId) throws ImageRetrievalException {
//         Path imagePath = null;
        
//         Path rootLocation = Paths.get(getRootLocationForUserProfileImageUpload(profileId));

//         try {
//             if (rootLocation.toFile().exists()) {
//                 Iterator<Path> iterator = Files.newDirectoryStream(rootLocation).iterator();
                
//                 if (iterator.hasNext()) {
//                     imagePath = iterator.next();                
//                 }            
//             }
//         } catch (IOException ie) {
//             throw new ImageRetrievalException(ie.getMessage());
//         }
        
//         return imagePath;
//     }
    
    
//     private void deleteAllFilesInDirectory(Path rootLocation) {
//         try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootLocation)) {
//             directoryStream.forEach(path -> {
//                 path.toFile().delete();
//             });
//         } catch (IOException ie) {
//             System.err.println("Problem trying to delete files in " + rootLocation.toString());
//         }
//     }
    
    
//     public void saveProfilePhoto(MultipartFile file, Profile profile) throws MissingFileException, FileTooLargeException, CopyFileException {
//         validateFile(file, maxFileUploadSize);
//         Path rootLocation = Paths.get(getRootLocationForUserProfileImageUpload(profile));
//         deleteAllFilesInDirectory(rootLocation);
//         saveFile(file, profile, rootLocation);
//     }

    
//     private void saveFile(MultipartFile file, Profile profile, Path rootLocation) throws CopyFileException {
//         try (InputStream is = file.getInputStream()) {
//             String newFileName = getNewFileName(file, profile);
//             Files.copy(is, rootLocation.resolve(newFileName));
//         } catch (IOException ie) {
//             throw new CopyFileException("Failed to upload!");
//         }
//     }
    
    
//     private void validateFile(MultipartFile file, Long maxFileUploadSize) throws MissingFileException, FileTooLargeException {
//         checkFileExistence(file);
//         checkFileSize(file, maxFileUploadSize);
//     }
    
    
//     private String getNewFileName(MultipartFile file, Profile profile) {
//         String newFileName = FileNameUtil.createFileName(profile, file.getOriginalFilename());
//         return newFileName;
//     }
    
       
//     public void checkFileSize(MultipartFile file, Long maxFileUploadSize) throws FileTooLargeException {
//         if (file.getSize() > maxFileUploadSize) {
//             String message = "File is too large - max size is " + maxFileUploadSize;
//             throw new FileTooLargeException(message);
//         }
//     }

    
//     public void checkFileExistence(MultipartFile file) throws MissingFileException {
//         if (file == null) throw new MissingFileException("No file sent!");
//         if (StringUtils.isEmpty(file.getName())) throw new MissingFileException("No file sent");
//     }
    
    
//     private void createDirectoryIfItDoesntExist(String dir) {
//         final Path path = Paths.get(dir);
        
//         if (Files.notExists(path)) {
//             try {
//                 Files.createDirectories(path);
//             } catch (IOException ie) {
//                 System.err.println("Problem creating directory " + dir);
//             }
//         } 
//     }
    
//     public String properSeparators(String filePath) {
//         if (filePath != null) {
//             String properPath = filePath.replaceAll("\\\\", "/");
//             return properPath;
//         } else {
//             return null;
//         }
//     }

    
//     public String getRootLocationForUserUpload(UUID profileId) {
//         if (StringUtils.isEmpty(profileId)) throw new IllegalArgumentException("No profile id");
        
//         StringBuilder builder = new StringBuilder();
        
//         builder.append(userFilesBasePath);
//         builder.append("/");
//         builder.append(profileId);
        
//         String location = builder.toString();
        
//         createDirectoryIfItDoesntExist(location);
        
//         return location;
//     }
    
        
//     public String getRootLocationForUserUpload(Profile profile) {
//         if (profile == null) throw new IllegalArgumentException("No profile provided");
//         return this.getRootLocationForUserUpload(profile.getId());
//     }
    

//     public String getRootLocationForUserProfileImageUpload(UUID profileId) {
//         if (StringUtils.isEmpty(profileId)) throw new IllegalArgumentException("No profile id");

//         String base = getRootLocationForUserUpload(profileId);
        
//         StringBuilder builder = new StringBuilder(base);
//         builder.append("/");
//         builder.append(PROFILE_DIR);
        
//         String location = builder.toString();
        
//         createDirectoryIfItDoesntExist(location);
        
//         return location;
//     }   
    
//     public String getRootLocationForUserProfileImageUpload(Profile profile) {
//         if (profile == null) throw new IllegalArgumentException("No profile provided");
//         return this.getRootLocationForUserProfileImageUpload(profile.getId());
//     }  