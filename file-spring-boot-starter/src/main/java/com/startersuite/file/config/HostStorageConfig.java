package com.startersuite.file.config;

import com.startersuite.file.service.FileMetaService;
import com.startersuite.file.service.FileService;
import com.startersuite.file.service.impl.HostStorageFileService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import java.io.File;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties(HostStorageProperties.class)
@ConditionalOnProperty(prefix = "file", name = "type", havingValue = "host")
public class HostStorageConfig {

    @Autowired
    private HostStorageProperties properties;
    @Autowired
    private FileMetaService fileMetaService;

    @Bean
    public FileService fileService() {
        if (StringUtils.isEmpty(properties.getPath())) {
            throw new RuntimeException("path not provide");
        }
        File file = new File(properties.getPath());
        if (!file.exists()) {
            throw new RuntimeException("directory not exist");
        }
        HostStorageFileService fileService = new HostStorageFileService();
        fileService.setPath(properties.getPath());
        fileService.setFileMetaService(fileMetaService);
        return fileService;
    }
}
