package com.startersuite.file.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileDownload {
    private byte[] content;
    private String name;
}
