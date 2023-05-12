package com.startersuite.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "amazons3")
public class S3StorageProperties {

    private String awsAccessKey;

    private String awsSecretKey;

    private String endpoint;

    private String bucketName;

}
