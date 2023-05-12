package com.startersuite.file.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.startersuite.file.entity.FileDownload;
import com.startersuite.file.entity.FileMeta;
import com.startersuite.file.service.FileMetaService;
import com.startersuite.file.service.FileService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Slf4j
public class FastDFSStorageFileService implements FileService {

    private FastFileStorageClient storageClient;
    private FileMetaService fileMetaService;

    @Override
    public String upload(byte[] bytes, String filename, String ext) {
        String hash = DigestUtils.md5DigestAsHex(bytes);
        String uniqueHash = uniqueHash(hash, filename, ext);
        FileMeta fileMeta = fileMetaService.getByUniqueHash(uniqueHash);
        if (fileMeta != null) {
            return uniqueHash;
        }
        fileMeta = fileMetaService.getByContentHash(hash);
        String filepath;
        if (fileMeta != null && !StringUtils.isEmpty(fileMeta.getFilepath())) {
            filepath = fileMeta.getFilepath();
        } else {
            InputStream in = new ByteArrayInputStream(bytes);
            StorePath storePath = storageClient.uploadFile(in, bytes.length, ext, null);
            filepath = storePath.getFullPath();
        }
        log.debug("filepath: {}", filepath);

        fileMetaService
                .save(FileMeta.builder().uniqueHash(uniqueHash).contentHash(hash).filepath(filepath)
                        .originName(filename).ext(ext).build());
        return uniqueHash;
    }

    @Override
    public FileDownload download(String uniqueHash) {
        FileMeta fileMeta = fileMetaService.getByUniqueHash(uniqueHash);
        if (fileMeta == null || StringUtils.isEmpty(fileMeta.getFilepath())) {
            throw new RuntimeException("没有此文件");
        }
        StorePath storePath = StorePath.parseFromUrl(fileMeta.getFilepath());
        byte[] bytes = storageClient
                .downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
        String name = fileMeta.getOriginName();
        if (!StringUtils.isEmpty(fileMeta.getExt())) {
            name = (name + "." + fileMeta.getExt());
        }
        FileDownload fileDownload = FileDownload.builder().content(bytes)
                .name(name).build();
        return fileDownload;
    }
}
