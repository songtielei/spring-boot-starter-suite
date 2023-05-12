package com.startersuite.file.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.startersuite.file.service.FileMetaService;
import com.startersuite.file.service.FileService;
import com.startersuite.file.service.impl.FastDFSStorageFileService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Getter
@Setter
@Configuration
@ConditionalOnProperty(prefix = "file", name = "type", havingValue = "fastDFS")
@Import(FdfsClientConfig.class)
public class FastDFSStorageConfig {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private FileMetaService fileMetaService;

    @Bean
    public FileService fileService() {
        FastDFSStorageFileService fileService = new FastDFSStorageFileService();
        fileService.setStorageClient(fastFileStorageClient);
        fileService.setFileMetaService(fileMetaService);
        return fileService;
    }
}
