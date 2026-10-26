package com.girardon.chatop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class PictureService {
    public String savePicture(MultipartFile picture, String path) throws IOException {
        String uniqueName = System.currentTimeMillis()
                + "_"
                + picture.getOriginalFilename();

        Path uploadPath = Path.of(path);
        Path picturePath = uploadPath.resolve(uniqueName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(picture.getInputStream(), picturePath, StandardCopyOption.REPLACE_EXISTING);


        return picturePath.toString();
    }

    public byte[] getPicture(String path) throws IOException {
        Path picturePath = Path.of(path);

        if (Files.exists(picturePath)) {
            return Files.readAllBytes(picturePath);
        } else {
            return null;
        }
    }
}
