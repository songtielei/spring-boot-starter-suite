package com.startersuite.file.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.startersuite.file.config.HostStorageConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@ActiveProfiles("host")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HostStorageConfig.class)
public class HostStorageFileServiceTest {

    @MockBean
    private FileMetaService fileMetaService;
    @Autowired
    private FileService fileService;

    @Test
    public void testUpload() {
        byte[] bytes = new byte[10];
        bytes[1] = 1;
        String filename = "name";
        String ext = "jpg";
        String hash = DigestUtils.md5DigestAsHex(bytes);
        String uniqueHash = fileService.uniqueHash(hash, filename, ext);
        when(fileMetaService.getByUniqueHash(uniqueHash)).thenReturn(null);
        when(fileMetaService.getByContentHash(hash)).thenReturn(null);

        String result = fileService.upload(bytes, filename, ext);
        assertThat(result).isEqualTo(uniqueHash);

    }
}
