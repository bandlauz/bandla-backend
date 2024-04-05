package uz.bandla.service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface StorageService {
    void upload(String fileOriginalName, InputStream inputStream, ObjectMetadata metadata);
}