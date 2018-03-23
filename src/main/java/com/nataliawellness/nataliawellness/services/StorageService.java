package com.nataliawellness.nataliawellness.services;


import com.nataliawellness.nataliawellness.exceptions.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    private String location = "upload-dir";

    private Path rootLocation;

    public void store(MultipartFile file) {


        rootLocation = Paths.get(location);

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        filename = filename.toLowerCase().replaceAll(" ", "-");

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

}
