package uz.bandla.service.impl;

import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.Response;
import uz.bandla.dto.file.FileDTO;
import uz.bandla.entity.FileEntity;
import uz.bandla.mapper.FileMapper;
import uz.bandla.repository.FileRepository;
import uz.bandla.service.FileService;
import uz.bandla.service.StorageService;
import uz.bandla.util.ProfileUtil;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final StorageService storageService;
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Value("${aws.s3.url}")
    private String s3Url;

    @Override
    public ResponseEntity<Response<FileDTO>> upload(MultipartFile multipartFile) {
        UUID id = UUID.randomUUID();
        String url = s3Url + id;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());
            storageService.upload(String.valueOf(id), multipartFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileEntity entity = new FileEntity(id, url, ProfileUtil.getProfile());
        fileRepository.save(entity);

        return GoodResponse.ok(fileMapper.map(entity));
    }
}