package uz.bandla.controller;

import uz.bandla.dto.file.FileDTO;
import uz.bandla.service.FileService;
import uz.bandla.dto.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/file-store")
@RequiredArgsConstructor
@Tag(name = "File controller")
public class FileController {
    private final FileService fileService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for upload file")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<FileDTO>> upload(@RequestParam("file") MultipartFile file) {
        return fileService.upload(file);
    }
}