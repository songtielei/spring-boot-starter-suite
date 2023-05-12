package com.startersuite.file.service;

import com.startersuite.file.entity.FileDownload;

import java.nio.charset.StandardCharsets;
import org.springframework.util.DigestUtils;

public interface FileService {
    /**
     * 上传文件
     * @param file 文件内容
     * @param filename 原始文件名
     * @param ext 文件扩展名
     * @return key
     */
    String upload(byte[] file, String filename, String ext);

    /**
     * 下载文件
     * @param uniqueHash uniqueHash
     * @return
     */
    FileDownload download(String uniqueHash);

    default String uniqueHash(String contentHash, String filename, String ext) {
        String hash = DigestUtils.md5DigestAsHex((contentHash + filename + ext).getBytes(
                StandardCharsets.UTF_8));
        return hash;
    }

}
