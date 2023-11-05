package uz.bandla.controller;

import uz.bandla.dto.file.FileDTO;
import uz.bandla.service.FileService;
import uz.bandla.dto.Response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file-store")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Response<FileDTO>> upload(@RequestParam("file") MultipartFile file) {
        return fileService.upload(file);
    }
}