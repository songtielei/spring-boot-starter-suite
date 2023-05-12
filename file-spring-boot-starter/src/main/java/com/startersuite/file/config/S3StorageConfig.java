package com.startersuite.file.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.startersuite.file.service.FileMetaService;
import com.startersuite.file.service.FileService;
import com.startersuite.file.service.impl.S3StorageFileService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties(S3StorageProperties.class)
@ConditionalOnProperty(prefix = "file", name = "type", havingValue = "s3")
public class S3StorageConfig {

    @Autowired
    private S3StorageProperties s3StorageProperties;
    @Autowired
    private FileMetaService fileMetaService;

    @Bean
    public FileService fileService() {

        S3StorageFileService fileService = new S3StorageFileService();
        fileService.setAmazonS3(amazonS3());
        fileService.setFileMetaService(fileMetaService);
        fileService.setBucketName(s3StorageProperties.getBucketName());
        return fileService;
    }

    private AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(s3StorageProperties.getAwsAccessKey(),
                s3StorageProperties.getAwsSecretKey());

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withClientConfiguration(new ClientConfiguration().withProtocol(Protocol.HTTP))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        s3StorageProperties.getEndpoint(), Regions.CN_NORTH_1.name()))
                .build();
        return s3Client;

    }
}
