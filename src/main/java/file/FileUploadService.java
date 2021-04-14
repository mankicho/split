package file;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Log4j2
@Component
public class FileUploadService {

    public int fileUpload(String path, MultipartFile multipartFile) {
        String ext = getExtension(multipartFile);
        if (ext == null) {
            return -1; // 확장자 없음
        }
        File targetFile = new File(path + getUUID() + ext); // file 생성
        try {
            InputStream fileStream = multipartFile.getInputStream(); // file 업로드
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        if (targetFile.exists()) {
            log.info(targetFile.getAbsolutePath());
            return 202; // 파일 업로드 성공
        }
        return 500; // 파일업로드 실패
    }

    private String getUUID() { // 고유 식별자 만들기
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String getExtension(MultipartFile multipartFile) { // 확장자 가져오기
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return null;
    }
}
