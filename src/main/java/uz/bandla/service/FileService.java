package uz.bandla.service;

import uz.bandla.dto.Response;
import uz.bandla.dto.file.FileDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseEntity<Response<FileDTO>> upload(MultipartFile multipartFile);
}