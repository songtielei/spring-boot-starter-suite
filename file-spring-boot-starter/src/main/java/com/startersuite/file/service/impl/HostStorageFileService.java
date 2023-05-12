package com.startersuite.file.service.impl;

import com.startersuite.file.entity.FileDownload;
import com.startersuite.file.entity.FileMeta;
import com.startersuite.file.service.FileMetaService;
import com.startersuite.file.service.FileService;
import java.io.File;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Slf4j
public class HostStorageFileService implements FileService {

    private String path;
    private FileMetaService fileMetaService;

    @Override
    public String upload(byte[] bytes, String filename, String ext) {
        log.debug("path: {}", path);
        String hash = DigestUtils.md5DigestAsHex(bytes);
        String uniqueHash = uniqueHash(hash, filename, ext);
        FileMeta fileMeta = fileMetaService.getByUniqueHash(uniqueHash);
        if (fileMeta != null) {
            return uniqueHash;
        }
        fileMeta = fileMetaService.getByContentHash(hash);
        if (fileMeta == null) {
            File file = getFile(hash);
            if (!file.getParentFile().exists()) {
                boolean f = file.getParentFile().mkdirs();
                if (!f) {
                    throw new RuntimeException("上传文件失败: 目录无法创建");
                }
            }
            try {
                FileCopyUtils.copy(bytes, file);
            } catch (IOException e) {
                throw new RuntimeException("上传文件失败: 文件无法保存", e);
            }
        }
        fileMetaService
                .save(FileMeta.builder().uniqueHash(uniqueHash).contentHash(hash).filepath(hash)
                        .originName(filename).ext(ext).build());

        return uniqueHash;
    }

    @Override
    public FileDownload download(String uniqueHash) {
        FileMeta fileMeta = fileMetaService.getByUniqueHash(uniqueHash);
        File file = getFile(fileMeta.getContentHash());
        if (!file.exists()) {
            throw new RuntimeException("file:" + uniqueHash + " not exist");
        }
        byte[] bytes;
        try {
            bytes = FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String name = fileMeta.getOriginName();
        if (!StringUtils.isEmpty(fileMeta.getExt())) {
            name = (name + "." + fileMeta.getExt());
        }
        FileDownload fileDownload = FileDownload.builder().content(bytes)
                .name(name).build();
        return fileDownload;
    }

    private File getFile(String contentHash) {
        File file = new File(
                path + File.separator + contentHash.substring(0, 2) + File.separator
                        + contentHash.substring(2));
        return file;
    }
}
