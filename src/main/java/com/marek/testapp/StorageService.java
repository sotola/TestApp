package com.marek.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
public class StorageService {
    HashMap<Path, FileSystemResource> fileStore = new HashMap<Path, FileSystemResource>();
    Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Autowired
    public StorageService() {}

    void store(MultipartFile file) {
        try {
            File uploadedFile = Files.createTempFile("", "").toFile();
            file.transferTo(uploadedFile);
            fileStore.put(Paths.get(file.getOriginalFilename()), new FileSystemResource(uploadedFile));
        } catch (IOException e) {
            logger.warn(e.getLocalizedMessage());
        }
    }

    Stream<Path> loadAll() {
        return fileStore.keySet().stream();
    }

    Path load(String filename) {
        Path path = Paths.get(filename);

        if (fileStore.containsKey(path)) {
            return path;
        }

        return null;
    }

    Resource loadAsResource(String filename) {
        return fileStore.get(Paths.get(filename));
    }

    void deleteAll() {
        fileStore.clear();
    }
}
