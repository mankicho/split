package upload;

import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Log4j
@Component
public class FileUploadService {

    public int fileUpload(HttpServletRequest req, MultipartFile multipartFile) {
        log.info(LocalDateTime.now() + ": file upload request");
        int savedFileNumber = 0;
        String path = req.getSession().getServletContext().getRealPath("/resources") + "/profile/"; // 파일 경로
        File targetFile = new File(path + multipartFile.getOriginalFilename());
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            log.info("upload success");
            savedFileNumber++;
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        log.info("file upload finish");
        log.info("----------------");
        return savedFileNumber;
    }
}
