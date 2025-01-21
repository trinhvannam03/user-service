package com.project.userservice.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class MediaDTO {
    private String secureUrl;
    private String fileName;
    private String format;
    private String width;
    private String height;
    private LocalDateTime createdAt;
    private String publicId;
    private String assetId;
    public String resourceType;
}
