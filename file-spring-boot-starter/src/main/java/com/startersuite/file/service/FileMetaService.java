package com.startersuite.file.service;

import com.startersuite.file.entity.FileMeta;

public interface FileMetaService {

    void save(FileMeta fileMeta);

    FileMeta getByUniqueHash(String uniqueHash);

    /**
     * 根据 contentHash 返回最多一条 FileMeta
     * contentHash 可能对应多条文件名不同的 FileMeta
     * @param contentHash contentHash
     * @return
     */
    FileMeta getByContentHash(String contentHash);
}
