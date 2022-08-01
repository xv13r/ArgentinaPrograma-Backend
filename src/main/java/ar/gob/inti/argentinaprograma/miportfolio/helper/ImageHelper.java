package ar.gob.inti.argentinaprograma.miportfolio.helper;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class ImageHelper {
    private Base64MultipartFile base64MultipartFile;
    public Base64MultipartFile base64ToImage(String encodedBase64, String fileName) throws  IOException {
		
        
        String encodeImg = encodedBase64.substring(encodedBase64.indexOf(",") + 1);
        byte[] decodedBytes = Base64Utils.decodeFromString(encodeImg);
        base64MultipartFile = new Base64MultipartFile(decodedBytes, fileName);
        try {
             
            base64MultipartFile.transferTo(base64MultipartFile.getFile());
            
        }catch (IOException e) {
            throw new IOException("IOExeption :"+e);
        }

        return base64MultipartFile;

    }
}