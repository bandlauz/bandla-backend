package uz.bandla.mapper;

import org.springframework.stereotype.Component;
import uz.bandla.dto.file.FileDTO;
import uz.bandla.entity.FileEntity;

@Component
public class FileMapper {

    public FileDTO map(FileEntity entity) {
        return new FileDTO(entity.getId().toString(), entity.getUrl());
    }
}