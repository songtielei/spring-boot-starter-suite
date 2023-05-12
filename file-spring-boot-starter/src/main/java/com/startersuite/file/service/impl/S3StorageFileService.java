package com.startersuite.file.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.startersuite.file.entity.FileDownload;
import com.startersuite.file.entity.FileMeta;
import com.startersuite.file.service.FileMetaService;
import com.startersuite.file.service.FileService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Slf4j
public class S3StorageFileService implements FileService {

    private AmazonS3 amazonS3;
    private FileMetaService fileMetaService;
    private String bucketName;

    @Override
    public String upload(byte[] bytes, String filename, String ext) {
        String hash = DigestUtils.md5DigestAsHex(bytes);
        String uniqueHash = uniqueHash(hash, filename, ext);
        FileMeta fileMeta = fileMetaService.getByUniqueHash(uniqueHash);
        if (fileMeta != null) {
            return uniqueHash;
        }
        fileMeta = fileMetaService.getByContentHash(hash);
        if (fileMeta == null) {
            if (!amazonS3.doesBucketExistV2(bucketName)) {
                amazonS3.createBucket(bucketName);
            }
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);
            amazonS3.putObject(bucketName, hash, input, new ObjectMetadata());
        }
        fileMetaService
                .save(FileMeta.builder().uniqueHash(uniqueHash).contentHash(hash).filepath(hash)
                        .originName(filename).ext(ext).build());
        return uniqueHash;
    }

    @Override
    public FileDownload download(String uniqueHash) {
        FileMeta fileMeta = fileMetaService.getByUniqueHash(uniqueHash);
        GetObjectRequest request = new GetObjectRequest(bucketName, fileMeta.getContentHash());
        S3ObjectInputStream inputStream = amazonS3.getObject(request).getObjectContent();

        byte[] bytes = readInputStream(inputStream);
        String name = fileMeta.getOriginName();
        if (!StringUtils.isEmpty(fileMeta.getExt())) {
            name = (name + "." + fileMeta.getExt());
        }
        FileDownload fileDownload = FileDownload.builder().content(bytes)
                .name(name).build();
        return fileDownload;
    }

    private byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            FileCopyUtils.copy(inputStream, bos);
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }

        return bos.toByteArray();
    }

}
