package firebase;

import com.google.api.client.auth.oauth2.Credential;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import firebase.response.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
@RequiredArgsConstructor
public class FirebaseCloudStorageService {

    private final FirebaseUtil firebaseUtil;

    public FileUploadResponse uploadFile(String filePath, MultipartFile multipartFile) {
        log.info(multipartFile.getOriginalFilename());
        firebaseUtil.initializeApp();
        Bucket bucket = StorageClient.getInstance().bucket();
        String name = generateFileName(multipartFile.getOriginalFilename());
        try {
            bucket.create(name, multipartFile.getBytes(), multipartFile.getContentType());
        } catch (Exception e) {
            // todo 1. 오류나는 이유 분석하기
            e.printStackTrace();
        }

        return FileUploadResponse.builder()
                .fileName(name)
                .msg("파일업로드")
                .status(202)
                .build();
    }

    public String getFileDownloadingURL() {
        firebaseUtil.initializeApp();
        Credentials credentials = firebaseUtil.getCredentials();
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("paidagogos-edac9.appspot.com", "profile/manki/c/dasdwcsdvsrdhsxdrchngyctjfhrg/페이코 오류.jpg"));
        URL signedURL = storage.signUrl(blob, 30, TimeUnit.DAYS);
        log.info(signedURL);
        log.info(signedURL.toExternalForm());
        log.info(signedURL.toString().length());
        return signedURL.toExternalForm();

    }

    private String generateFileName(String originalFileName) {
        return "profile/manki/" + UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(originalFileName);
    }
}
