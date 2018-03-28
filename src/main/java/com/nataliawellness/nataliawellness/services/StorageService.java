package com.nataliawellness.nataliawellness.services;


import com.nataliawellness.nataliawellness.entities.Media;
import com.nataliawellness.nataliawellness.exceptions.StorageException;
import com.nataliawellness.nataliawellness.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${app.image.cdn}")
    private String imageCDN;

    @Value("${app.upload.path}")
    private String uploadPath;


    @Autowired
    MediaRepository mediaRepository;

    private Path rootLocation;

    public String store(MultipartFile file) {

        String storedLocation;


        rootLocation = Paths.get(uploadPath);

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

            //save to db
            Media media = new Media();
            media.setName(imageCDN+file.getOriginalFilename());
            mediaRepository.save(media);

            storedLocation = media.getName();

        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }


        return storedLocation;
    }


}
