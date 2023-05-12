package com.startersuite.file.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileMeta {
    private String uniqueHash;
    private String contentHash;
    private String filepath;
    private String originName;
    private String ext;
}
