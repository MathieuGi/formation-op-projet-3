package com.girardon.chatop.service;

import com.girardon.chatop.exception.EntityNotFoundException;
import com.girardon.chatop.exception.FileException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class PictureService {
    String UPLOAD_DIRECTORY = "src/main/resources/images/";

    public String savePicture(MultipartFile picture) {
        String uniqueName = System.currentTimeMillis()
                + "_"
                + picture.getOriginalFilename();

        Path uploadPath = Path.of(UPLOAD_DIRECTORY);
        Path picturePath = uploadPath.resolve(uniqueName);


        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(picture.getInputStream(), picturePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileException("An error occurs when save your image");
        }


        return uniqueName;
    }

    public byte[] getPicture(String name) {
        byte[] bytes;
        Path uploadPath = Path.of(UPLOAD_DIRECTORY);
        Path picturePath = uploadPath.resolve(name);
        try {
            bytes = Files.readAllBytes(picturePath);
        } catch (IOException e) {
            throw new EntityNotFoundException("Image not found");
        }
        return bytes;
    }
}
