package com.project.userservice.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.userservice.dtos.MediaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    //NoSuchFileException
    public List<String> batchUpload(List<MultipartFile> files) {
        List<String> result = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                System.out.println(Arrays.toString(file.getBytes()));
                Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                String fileUrl = uploadResult.get("secure_url").toString();
                result.add(fileUrl);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return result;
    }

    public MediaDTO upload(MultipartFile file) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String secureUrl = uploadResult.get("secure_url").toString();
            String assetId = uploadResult.get("asset_id").toString();
            String format = uploadResult.get("format").toString();
            String publicId = uploadResult.get("public_id").toString();
            String resourceType = uploadResult.get("resource_type").toString();
            return MediaDTO.builder()
                    .createdAt(LocalDateTime.now())
                    .resourceType(resourceType)
                    .secureUrl(secureUrl)
                    .assetId(assetId)
                    .publicId(publicId)
                    .format(format)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
