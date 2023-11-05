package uz.bandla.mapper;

import uz.bandla.dto.file.FileDTO;
import uz.bandla.entity.FileEntity;

import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileDTO map(FileEntity entity) {
        return new FileDTO(entity.getId().toString(), entity.getUrl());
    }
}