package uz.bandla.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.entity.FileEntity;
import uz.bandla.favor.FileFavor;
import uz.bandla.repository.FileRepository;

@Favor
public class FileFavorImpl implements FileFavor {

    private final FileRepository repository;

    public FileFavorImpl(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(FileEntity entity) {
        repository.save(entity);
    }
}