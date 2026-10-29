package com.girardon.chatop.controller;

import com.girardon.chatop.service.PictureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/images")
@RestController
@Tag(name = "Images")
@RequiredArgsConstructor
public class ImagesController {
    private final PictureService pictureService;

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String name) {
        byte[] bytes = pictureService.getPicture(name);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
    }
}
